package top.erhuoduoduo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.erhuoduoduo.entity.ResultModel;
import top.erhuoduoduo.entity.Sections;

import java.util.List;

public interface SectionService extends IService<Sections> {
    List<Sections> search() throws Exception;
}
