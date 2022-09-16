package DAO;

import top.erhuoduoduo.entity.Report;
import top.erhuoduoduo.entity.Sections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 版块接口实现类
 * @author: collapsar
 * @create: 2022/03/12 00:13
 */
public class SectionDaoImpl implements SectionDao{

    @Override
    public List<Sections> querySectionList() {
        // 数据库链接
        Connection connection = null;
        // 预编译statement
        PreparedStatement preparedStatement = null;
        // 结果集
        ResultSet resultSet = null;
        // 商品列表
        List<Sections> list = new ArrayList<Sections>();
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 连接数据库
            //todo
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/articlesearch?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL",
                            "articlesearch", "root");
            String sql = "SELECT id,sectionName FROM section";
            // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 获取结果集
            resultSet = preparedStatement.executeQuery();
            // 结果集解析
            while (resultSet.next()) {
                Sections sections = new Sections();
                sections.setId(resultSet.getInt("id"));
                sections.setSectionName(resultSet.getString("sectionName"));
                list.add(sections);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
