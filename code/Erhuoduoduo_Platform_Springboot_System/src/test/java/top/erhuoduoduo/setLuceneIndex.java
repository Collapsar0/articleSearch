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
 * @description: ��������
 * @author: collapsar
 * @create: 2022/03/07 23:06
 */
public class setLuceneIndex {
//    @Test
    public void createIndexTest() throws Exception {
    // 1. �ɼ�����
        ReportDao reportDao = new ReportDaoImpl();
        List<Report> reportList = reportDao.queryReportList();
    // 2. ����Document�ĵ�����
        List<Document> documents = new ArrayList<Document>();
        for (Report report : reportList) {
            Document document = new Document();
            // Document�ĵ������Field��
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
            // ��Document�ŵ�list��
            documents.add(document);
        }
        // 3. ����Analyzer�ִ���,�����ĵ������ĵ����зִ�
        Analyzer analyzer = new IKAnalyzer();
        // 4. ����Directory����,�����������λ��
        Directory directory = FSDirectory.open(Paths.get("E:\\LuceneDir"));
        // 5. ����IndexWriteConfig����д��������Ҫ������
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        // 6.����IndexWriterд�����
        IndexWriter indexWriter = new IndexWriter(directory, config);
        // 7.д�뵽�����⣬ͨ��IndexWriter����ĵ�����document
        for (Document doc : documents) {
            indexWriter.addDocument(doc);
        }
        // 8.�ͷ���Դ
        indexWriter.close();
    }

}
