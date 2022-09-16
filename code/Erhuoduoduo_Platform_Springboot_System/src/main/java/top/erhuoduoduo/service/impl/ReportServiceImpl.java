package top.erhuoduoduo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;
import top.erhuoduoduo.entity.Report;
import top.erhuoduoduo.entity.ResultModel;
import top.erhuoduoduo.mapper.ReportMapper;
import top.erhuoduoduo.service.ReportService;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 实现类
 * @author: collapsar
 * @create: 2022/03/08 21:20
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
    //设置每页查询10条数据
    public final static Integer PAGE_SIZE = 10;

    @Override
    public ResultModel search(String section, String stockCode,String companyName,String year,
                              String fileName,String content,Integer page,String fileSource) throws Exception{
        /**
         * 1. 需要使用的对象封装
         */
        ResultModel resultModel = new ResultModel();
        List<Report> reportArrayList = new ArrayList<>();
        String[] contentArray = content.split(",");

        //从第几条开始查询
        Integer start;
        //查询到多少条数据为止
        Integer end;

        if(page==-31901224){
            //从第几条开始查询
            start = 0;
            //查询到多少条数据为止
            end = 99999999;
        } else {
            //从第几条开始查询
            start = (page - 1) * PAGE_SIZE;
            //查询到多少条数据为止
            end = page * PAGE_SIZE;
        }


        // 创建分词器
        Analyzer analyzer = new IKAnalyzer();
        // 创建组合搜索对象
        BooleanQuery.Builder builder = new BooleanQuery.Builder();

        /**
         * 2. 根据关键字搜索条件封装
         */
        // 创建文本搜索对象
        QueryParser queryParser = new QueryParser("stockCode", analyzer);
        //条件1
        Query query1 = null;
        if (StringUtils.isEmpty(stockCode)) {
        // 如果查询关键字为空, 查询所有
            query1 = queryParser.parse("*:*");
        } else {
        // 根据关键字查询条件设置
            query1 = queryParser.parse(stockCode);
        }
        //将文本搜索对象加入到组合查询对象中
        builder.add(new BooleanClause(query1, BooleanClause.Occur.MUST));

        //补充条件 版块
        QueryParser queryParser4Section = new QueryParser("section", analyzer);
        Query query4Section = null;
        if (StringUtils.isEmpty(section)) {
            // 如果查询关键字为空, 查询所有
            query4Section = queryParser4Section.parse("*:*");
        } else {
            // 根据关键字查询条件设置
            query4Section = queryParser4Section.parse(section);
        }
        //将文本搜索对象加入到组合查询对象中
        builder.add(new BooleanClause(query4Section, BooleanClause.Occur.MUST));

        //补充条件 来源
        QueryParser queryParser4Source = new QueryParser("fileSource", analyzer);
        Query query4Source = null;
        if (StringUtils.isEmpty(fileSource)) {
            // 如果查询关键字为空, 查询所有
            query4Source = queryParser4Source.parse("*:*");
        } else {
            // 根据关键字查询条件设置
            query4Source = queryParser4Source.parse(fileSource);
        }
        //将文本搜索对象加入到组合查询对象中
        builder.add(new BooleanClause(query4Source, BooleanClause.Occur.MUST));


        //条件2
        QueryParser queryParser2 = new QueryParser("companyName", analyzer);
        Query query2 = null;
        if (StringUtils.isEmpty(companyName)) {
            // 如果查询关键字为空, 查询所有
            query2 = queryParser2.parse("*:*");
        } else {
            // 根据关键字查询条件设置
            query2 = queryParser2.parse("\""+companyName+"\"");
        }
        //将文本搜索对象加入到组合查询对象中
        builder.add(new BooleanClause(query2, BooleanClause.Occur.MUST));

        //条件3
        String [] yearRange = year.split("-");
        Query yearQuery = IntPoint.newRangeQuery("year", Integer.parseInt(yearRange[0]), Integer.parseInt(yearRange[1]));
        //将文本搜索对象加入到组合查询对象中
        builder.add(new BooleanClause(yearQuery, BooleanClause.Occur.MUST));


        //条件4
        QueryParser queryParser4 = new QueryParser("fileName", analyzer);
        Query query4 = null;
        if (StringUtils.isEmpty(fileName)) {
            // 如果查询关键字为空, 查询所有
            query4 = queryParser4.parse("*:*");
        } else {
            // 根据关键字查询条件设置
            query4 = queryParser4.parse(fileName);
        }
        //将文本搜索对象加入到组合查询对象中
        builder.add(new BooleanClause(query4, BooleanClause.Occur.MUST));

        //条件5 正文内容匹配
        QueryParser queryParser5 = new QueryParser("content", analyzer);
        Query query5 = null;
        if (StringUtils.isEmpty(content)) {
            // 如果查询关键字为空, 查询所有
            query5 = queryParser5.parse("*:*");
            builder.add(new BooleanClause(query5, BooleanClause.Occur.MUST));
        } else {
            // 根据关键字查询条件设置
            BooleanQuery.Builder builderContent = new BooleanQuery.Builder();

            for (int i=0;i<contentArray.length;i++){
//                builder.add(new BooleanClause(queryParser5.parse("\""+contentArray[i]+"\""), BooleanClause.Occur.MUST));
                builderContent.add(new BooleanClause(queryParser5.parse("\""+contentArray[i]+"\""), BooleanClause.Occur.SHOULD));
            }
            builder.add(builderContent.build(), BooleanClause.Occur.MUST);
        }
        //将文本搜索对象加入到组合查询对象中
        //builder.add(new BooleanClause(query5, BooleanClause.Occur.SHOULD));

        // 4. 创建Directory流对象,声明索引库位置
        // todo
        // Directory directory = FSDirectory.open(Paths.get("E:\\LuceneDir"));
        Directory directory = FSDirectory.open(Paths.get("/home/buiz/LucenceIndex"));

        // 5. 创建索引读取对象IndexReader
        IndexReader reader = DirectoryReader.open(directory);

        // 6. 创建索引搜索对象
        IndexSearcher searcher = new IndexSearcher(reader);
        // 7. 使用索引搜索对象，执行搜索，返回结果集TopDocs
        // 第一个参数：搜索对象，第二个参数：返回的数据条数，指定查询结果最顶部的n条数据返回
        TopDocs topDocs = searcher.search(builder.build(), end);
        System.out.println("查询到的数据总条数是：" + topDocs.totalHits);
        //8. 获取查询到的总条数
        resultModel.setReportCount(topDocs.totalHits);
        //9. 获取查询结果集
        ScoreDoc[] docs = topDocs.scoreDocs;
        //10. 解析结果集
        long maxCount;
        if(topDocs.totalHits<end){
            maxCount=topDocs.totalHits;
        }else{
            maxCount=end;
        }
        for (int i = start; i < maxCount; i++) {
        //读取文档
            Document doc = reader.document(docs[i].doc);
        //封装查询到的结果数据
            Report report = new Report();
            report.setId(Integer.valueOf(doc.get("id")));
            report.setStockCode(doc.get("stockCode"));
            report.setCompanyName(doc.get("companyName"));
            report.setSection(doc.get("section"));
            report.setYear(doc.get("year"));
            report.setPublicDate(doc.get("publicDate"));
            report.setFileName(doc.get("fileName"));
            report.setPageNumber(Integer.valueOf(doc.get("pageNumber")));
            report.setFileSource(doc.get("fileSource"));
//            report.setContent(doc.get("content"));
//            report.setContent(null);
//            reportArrayList.add(report);

            // 词频统计
//            Terms terms = reader.getTermVector(Integer.parseInt(doc.get("id"))-1,"content");
            Terms terms = reader.getTermVector(docs[i].doc,"content");
            if(terms ==null )
                continue;
//            System.out.println(terms.getSumDocFreq());
            TermsEnum termsEnum = terms.iterator();
            BytesRef thisTerm =null;



            Map<String,Integer> map= new HashMap<String,Integer>();
            Map<String,Integer> resultMap= new HashMap<String,Integer>();
            for (int index=0;index<contentArray.length;index++){
                map.put(contentArray[index],0);
                resultMap.put(contentArray[index],-1);
            }
            while ((thisTerm = termsEnum.next()) != null) {
                String termText = thisTerm.utf8ToString();
                long tf = termsEnum.totalTermFreq();

//                // 如果Map中的关键词包含了term的子串，则取更小的值
//                for(String key:map.keySet()){
//                    if(key.indexOf(termText)!=-1 && termText.length()>=2){
////                        System.out.println(key+" "+termText+" "+Math.toIntExact(tf));
//                        if(map.get(key) > Math.toIntExact(tf) || map.get(key)==0){
//                            map.put(key,Math.toIntExact(tf));
////                            System.out.println(Math.toIntExact(tf));
//                        }
//                    }
//                }

                if(map.containsKey(termText)){
                    map.put(termText, Math.toIntExact(tf));
                    resultMap.put(termText, Math.toIntExact(tf));
                }
            }

//            // 将未匹配到的词组进行子串赋值
//            for(String Key:resultMap.keySet()){
//                if(resultMap.get(Key) == -1 && map.get(Key) > 0 ){
//                    resultMap.put(Key,map.get(Key));
//                } else if(resultMap.get(Key) == -1){
//                    resultMap.put(Key,0);
//                }
//            }

//            // 循环遍历map,若所有关键词匹配数皆为0,则不放入结果集
//            Integer flag = 0;
//            for(String Key:map.keySet()){
//                if(map.get(Key) > flag){
//                    flag = map.get(Key);
//                }
//            }
//
//            if(flag > 0) {
//                report.setContent(map.toString());
//                reportArrayList.add(report);
//            }

            report.setContent(map.toString());
            reportArrayList.add(report);
        }
    /**
    * 11. 封装返回的结果集
    */
    //. 结果集
        resultModel.setReportList(reportArrayList);
    //当前页
        resultModel.setCurPage(page);
    //总页数
        Long pageCount = topDocs.totalHits % PAGE_SIZE > 0 ? (topDocs.totalHits
                / PAGE_SIZE) + 1 :topDocs.totalHits / PAGE_SIZE;
        resultModel.setPageCount(pageCount);

        reader.close();

        return  resultModel;
    }
}
