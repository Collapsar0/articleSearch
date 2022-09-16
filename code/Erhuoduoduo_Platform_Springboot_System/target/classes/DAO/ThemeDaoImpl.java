package DAO;

import top.erhuoduoduo.entity.Sections;
import top.erhuoduoduo.entity.Theme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 主题操作实现
 * @author: collapsar
 * @create: 2022/03/12 02:27
 */
public class ThemeDaoImpl implements ThemeDao{
    @Override
    public List<Theme> queryThemeList() {
        // 数据库链接
        Connection connection = null;
        // 预编译statement
        PreparedStatement preparedStatement = null;
        // 结果集
        ResultSet resultSet = null;
        // 商品列表
        List<Theme> list = new ArrayList<Theme>();
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 连接数据库
            //todo
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/articlesearch?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL",
                            "articlesearch", "root");
            String sql = "SELECT id,themeName FROM theme";
            // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 获取结果集
            resultSet = preparedStatement.executeQuery();
            // 结果集解析
            while (resultSet.next()) {
                Theme theme = new Theme();
                theme.setId(resultSet.getInt("id"));
                theme.setThemeName(resultSet.getString("themeName"));
                list.add(theme);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addTheme(String newTheme) {
        // 数据库链接
        Connection connection = null;
        // 预编译statement
        PreparedStatement preparedStatement = null;
        // 结果集
        ResultSet resultSet = null;

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 连接数据库
            //todo
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/articlesearch?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL",
                            "articlesearch", "root");
            String sql = "SELECT id,themeName FROM theme where themeName = '"+newTheme+"'";
            // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 获取结果集
            resultSet = preparedStatement.executeQuery();
            // 结果集解析
            if (resultSet.next()){
                connection.close();
                return false;
            } else {
                String sql2 = "insert into theme(themeName) values ('"+newTheme+"')";
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.execute();
                connection.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteTheme(String deleteThemName) {
        // 数据库链接
        Connection connection = null;
        // 预编译statement
        PreparedStatement preparedStatement = null;
        // 结果集
        ResultSet resultSet = null;

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 连接数据库
            //todo
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/articlesearch?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL",
                            "articlesearch", "root");
            String sql = "delete from theme where themeName = '"+deleteThemName+"'";
            // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 获取结果集
            preparedStatement.execute();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
