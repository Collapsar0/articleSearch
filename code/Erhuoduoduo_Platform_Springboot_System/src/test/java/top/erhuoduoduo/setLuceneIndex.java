package top.erhuoduoduo;

import DAO.ReportDao;
import DAO.ReportDaoImpl;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;
import top.erhuoduoduo.entity.Report;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 生成索引
 * @author: collapsar
 * @create: 2022/03/07 23:06
 */
public class setLuceneIndex {
//    @Test
    public void createIndexTest() throws Exception {
    // 1. 采集数据
        ReportDao reportDao = new ReportDaoImpl();
        List<Report> reportList = reportDao.queryReportList();
    // 2. 创建Document文档对象
        List<Document> documents = new ArrayList<Document>();
        for (Report report : reportList) {
            Document document = new Document();
            // Document文档中添加Field域
            document.add(new StringField("id", String.valueOf(report.getId()), Field.Store.YES));
            document.add(new StringField("stockCode", report.getStockCode(), Field.Store.YES));
            document.add(new TextField("section", report.getSection(), Field.Store.YES));
            document.add(new StringField("publicDate", report.getPublicDate(), Field.Store.YES));
            document.add(new TextField("companyName", report.getCompanyName(), Field.Store.YES));
//            document.add(new TextField("year", report.getYear(), Field.Store.YES));
            document.add(new IntPoint("year", Integer.parseInt(report.getYear())));
            document.add(new StoredField("year", Integer.parseInt(report.getYear())));
            document.add(new TextField("fileName", report.getFileName(), Field.Store.YES));
            document.add(new TextField("pageNumber", String.valueOf(report.getPageNumber()), Field.Store.YES));
            document.add(new TextField("fileSource", report.getFileSource(), Field.Store.YES));

            FieldType type = new FieldType();

            type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
            type.setStored(true);
            type.setStoreTermVectors(true);
            Field field = new Field("content", report.getContent(), type);
            document.add(field);

            //document.add(new TextField("content", report.getContent(), Field.Store.YES));
            // 把Document放到list中
            documents.add(document);
        }
        // 3. 创建Analyzer分词器,分析文档，对文档进行分词
        Analyzer analyzer = new IKAnalyzer();
        // 4. 创建Directory对象,声明索引库的位置
        Directory directory = FSDirectory.open(Paths.get("E:\\LuceneDir"));
        // 5. 创建IndexWriteConfig对象，写入索引需要的配置
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        // 6.创建IndexWriter写入对象
        IndexWriter indexWriter = new IndexWriter(directory, config);
        // 7.写入到索引库，通过IndexWriter添加文档对象document
        for (Document doc : documents) {
            indexWriter.addDocument(doc);
        }
        // 8.释放资源
        indexWriter.close();
    }

}
