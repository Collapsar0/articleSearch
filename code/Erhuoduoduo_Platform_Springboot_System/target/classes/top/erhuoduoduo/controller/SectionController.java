package top.erhuoduoduo.controller;

import com.alibaba.druid.util.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.erhuoduoduo.entity.ResultModel;
import top.erhuoduoduo.entity.Sections;
import top.erhuoduoduo.response.Result;
import top.erhuoduoduo.service.SectionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 版块接口
 * @author: collapsar
 * @create: 2022/03/12 00:41
 */
@ResponseBody
@RestController
@RequestMapping("/sections")
@Api(value = "提供版块查询的接口",tags = "版块管理")
public class SectionController {
    @Resource
    private SectionService sectionService;

    @GetMapping("/getSectionList")
    public Result getSectionList() throws Exception{

        List<Sections> sectionsList = sectionService.search();
        return Result.ok().data("sections",sectionsList);
    }
}
