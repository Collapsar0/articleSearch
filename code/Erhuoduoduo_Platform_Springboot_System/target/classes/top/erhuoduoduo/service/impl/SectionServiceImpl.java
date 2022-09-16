package top.erhuoduoduo.service.impl;

import DAO.SectionDao;
import DAO.SectionDaoImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.erhuoduoduo.entity.ResultModel;
import top.erhuoduoduo.entity.Sections;
import top.erhuoduoduo.mapper.SectionMapper;
import top.erhuoduoduo.service.SectionService;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 版块接口实现类
 * @author: collapsar
 * @create: 2022/03/12 00:28
 */
@Service
public class SectionServiceImpl extends ServiceImpl<SectionMapper, Sections> implements SectionService {

    @Override
    public List<Sections> search() throws Exception {
        SectionDao sectionDao = new SectionDaoImpl();
        List<Sections> sectionsList = sectionDao.querySectionList();

        return sectionsList;
    }
}
