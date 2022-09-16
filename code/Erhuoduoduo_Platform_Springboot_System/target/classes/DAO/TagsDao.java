package DAO;

import top.erhuoduoduo.entity.Tags;

import java.util.List;

public interface TagsDao {
    public List<Tags> queryTagsListByTheme(String themeName);

    public boolean updateTagsByTheme(String ThemeName,String Tags);
}
