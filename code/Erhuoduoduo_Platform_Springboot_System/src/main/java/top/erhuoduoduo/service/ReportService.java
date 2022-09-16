package top.erhuoduoduo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.erhuoduoduo.entity.Report;
import top.erhuoduoduo.entity.ResultModel;

public interface ReportService extends IService<Report> {
    ResultModel search(String section, String stockCode,String companyName,String year,
    String fileName,String content,Integer page,String fileSource) throws Exception;
}
