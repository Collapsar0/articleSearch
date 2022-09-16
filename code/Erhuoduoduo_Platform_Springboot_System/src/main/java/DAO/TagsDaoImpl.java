package DAO;

import top.erhuoduoduo.entity.Tags;
import top.erhuoduoduo.entity.Theme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: Tags实现
 * @author: collapsar
 * @create: 2022/03/12 04:32
 */
public class TagsDaoImpl implements TagsDao{
    @Override
    public List<Tags> queryTagsListByTheme(String themeName) {
        // 数据库链接
        Connection connection = null;
        // 预编译statement
        PreparedStatement preparedStatement = null;
        // 结果集
        ResultSet resultSet = null;
        // 商品列表
        List<Tags> list = new ArrayList<Tags>();
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 连接数据库
            //todo
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/articlesearch?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL",
                            "articlesearch", "root");
            String sql = "SELECT id,tagName,belong2Theme FROM tags where belong2Theme='"+themeName+"'";
            // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 获取结果集
            resultSet = preparedStatement.executeQuery();
            // 结果集解析
            while (resultSet.next()) {
                Tags tags = new Tags();
                tags.setId(resultSet.getInt("id"));
                tags.setTagName(resultSet.getString("tagName"));
                tags.setBelong2Theme(resultSet.getString("belong2Theme"));
                list.add(tags);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateTagsByTheme(String ThemeName, String tags) {
        // 数据库链接
        Connection connection = null;
        // 预编译statement
        PreparedStatement preparedStatement = null;
        // 结果集
        ResultSet resultSet = null;

        String [] newTags = tags.split(",");

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 连接数据库
            //todo
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/articlesearch?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL",
                            "articlesearch", "root");
            String sql = "delete from tags where belong2Theme ='"+ThemeName+"'";
            // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 获取结果集
            preparedStatement.execute();

            if(!tags.isEmpty()){
                for(int i=0;i<newTags.length;i++){
                    String sql2 = "insert into tags(tagName,belong2Theme) values ('"+newTags[i]+"','"+ThemeName+"')";
                    // 创建preparedStatement
                    preparedStatement = connection.prepareStatement(sql2);
                    // 获取结果集
                    preparedStatement.execute();
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();

        }


        return true;
    }
}
