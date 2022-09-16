package top.erhuoduoduo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.erhuoduoduo.entity.Sections;
import top.erhuoduoduo.entity.Tags;
import top.erhuoduoduo.entity.Theme;

import java.util.List;

public interface ThemeTagService extends IService<Theme> {
    // 获取所有主题
    List<Theme> getThemes();
    // 添加新主题
    boolean addTheme(String newTheme);
    // 删除主题
    boolean deleteTheme(String deleteTheme);
    // 根据主题获得Tags
    List<Tags> getTagsByTheme(String theme);
    // 根据主题更新Tags
    boolean updateTagsByTheme(String theme);
}
