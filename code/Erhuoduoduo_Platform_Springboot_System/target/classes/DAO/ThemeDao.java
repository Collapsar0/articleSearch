package DAO;

import top.erhuoduoduo.entity.Theme;

import java.util.List;

public interface ThemeDao {
    public List<Theme> queryThemeList();

    public boolean addTheme(String newTheme);

    public boolean deleteTheme(String deleteThemName);
}
