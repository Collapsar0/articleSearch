package top.erhuoduoduo.controller;

import com.alibaba.druid.util.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.erhuoduoduo.entity.ResultModel;
import top.erhuoduoduo.response.Result;
import top.erhuoduoduo.service.ReportService;

import javax.annotation.Resource;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 年报查询接口
 * @author: collapsar
 * @create: 2022/03/08 21:15
 */
@ResponseBody
@RestController
@RequestMapping("/reports")
@Api(value = "提供年报查询的接口",tags = "年报管理")
public class ReportController {
    @Resource
    private ReportService reportService;

    @GetMapping("/getReportList")
    public Result getReportList(String section, String stockCode,String companyName,String year,
                                String fileName,String content,Integer page,String fileSource) throws Exception{
        if (StringUtils.isEmpty(String.valueOf(page))) {
            page = 1;
        }
        if(page == -31901224){
            page = -31901224;
        }
        else if (page <= 0) {
            page = 1;
        }
        ResultModel resultModel = reportService.search(section, stockCode,companyName,
                year,fileName,content,page,fileSource);
        return Result.ok().data("reports",resultModel);
    }
}
