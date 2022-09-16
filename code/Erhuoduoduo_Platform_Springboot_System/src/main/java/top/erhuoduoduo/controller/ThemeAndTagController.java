package top.erhuoduoduo.controller;

import DAO.TagsDao;
import DAO.TagsDaoImpl;
import DAO.ThemeDao;
import DAO.ThemeDaoImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.erhuoduoduo.entity.Sections;
import top.erhuoduoduo.entity.Tags;
import top.erhuoduoduo.entity.Theme;
import top.erhuoduoduo.response.Result;

import java.util.List;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 主题与Tag接口
 * @author: collapsar
 * @create: 2022/03/12 02:42
 */
@ResponseBody
@RestController
@RequestMapping("/themeAndTag")
@Api(value = "主题与Tag接口",tags = "主题与Tag管理")
public class ThemeAndTagController {
    ThemeDao themeDao = new ThemeDaoImpl();
    TagsDao tagsDao = new TagsDaoImpl();

    @GetMapping("/getThemeList")
    public Result getThemeList() throws Exception{

        List<Theme> themeList = themeDao.queryThemeList();
        return Result.ok().data("themes",themeList);
    }

    @GetMapping("/addTheme")
    public Result addTheme(String newTheme) throws Exception{

        if(themeDao.addTheme(newTheme)){
            return Result.ok();
        } else {
            return Result.error().message("已存在相同主题");
        }

    }

    @GetMapping("/deleteTheme")
    public Result deleteTheme(String deleteTheme) throws Exception{

        if(themeDao.deleteTheme(deleteTheme)){
            tagsDao.updateTagsByTheme(deleteTheme,"");
            return Result.ok();
        } else {
            return Result.error().message("删除主题失败");
        }

    }

    @GetMapping("/getTagsList")
    public Result getTagsList(String themeName) throws Exception{

        List<Tags> tagsList = tagsDao.queryTagsListByTheme(themeName);
        return Result.ok().data("tags",tagsList);
    }

    @GetMapping("/updateTags")
    public Result updateTags(String themeName,String tags) throws Exception{

        tagsDao.updateTagsByTheme(themeName,tags);
        return Result.ok().message("关键词更新成功");
    }
}
