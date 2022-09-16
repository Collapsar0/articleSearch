package DAO;

import top.erhuoduoduo.entity.Report;

import java.util.List;

public interface ReportDao {
    /**
     * 查询所有的Report数据
     * @return
     **/
    public List<Report> queryReportList();

}
