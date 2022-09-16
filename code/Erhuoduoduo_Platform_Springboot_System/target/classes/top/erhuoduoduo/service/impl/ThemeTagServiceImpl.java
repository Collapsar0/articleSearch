package top.erhuoduoduo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.erhuoduoduo.entity.Tags;
import top.erhuoduoduo.entity.Theme;
import top.erhuoduoduo.mapper.ThemeMapper;
import top.erhuoduoduo.service.ThemeTagService;

import java.util.List;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 主题与Tag实现类
 * @author: collapsar
 * @create: 2022/03/12 02:13
 */
@Service
public class ThemeTagServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements ThemeTagService {

    @Override
    public List<Theme> getThemes() {
        return null;
    }

    @Override
    public boolean addTheme(String newTheme) {
        return false;
    }

    @Override
    public boolean deleteTheme(String deleteTheme) {
        return false;
    }

    @Override
    public List<Tags> getTagsByTheme(String theme) {
        return null;
    }

    @Override
    public boolean updateTagsByTheme(String theme) {
        return false;
    }
}
