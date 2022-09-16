package DAO;



import entity.Report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 接口实现类
 * @author: collapsar
 * @create: 2022/03/07 22:29
 */
public class ReportDaoImpl implements ReportDao{
    public List<Report> queryReportList() {
    // 数据库链接
        Connection connection = null;
    // 预编译statement
        PreparedStatement preparedStatement = null;
    // 结果集
        ResultSet resultSet = null;
    // 商品列表
        List<Report> list = new ArrayList<Report>();
        try {
        // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        // 连接数据库
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/articlesearch?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL",
                            "articlesearch", "root");
            String sql = "SELECT id,stockCode,companyName,Section,year,publicDate,fileName,content,pageNumber,fileSource FROM report";
        // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
        // 获取结果集
            resultSet = preparedStatement.executeQuery();
        // 结果集解析
            while (resultSet.next()) {
                Report report = new Report();
                report.setId(resultSet.getInt("id"));
                report.setStockCode(resultSet.getString("stockCode"));
                report.setCompanyName(resultSet.getString("companyName"));
                report.setSection(resultSet.getString("Section"));
                report.setYear(resultSet.getString("year"));
                report.setPublicDate(resultSet.getString("publicDate"));
                report.setFileName(resultSet.getString("fileName"));
                report.setContent(resultSet.getString("content"));
                report.setPageNumber(resultSet.getInt("pageNumber"));
                report.setFileSource(resultSet.getString("fileSource"));
                list.add(report);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
