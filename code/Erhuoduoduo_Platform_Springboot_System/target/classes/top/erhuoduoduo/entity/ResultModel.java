package top.erhuoduoduo.entity;

import java.util.List;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 查询返回分页数据
 * @author: collapsar
 * @create: 2022/03/08 21:12
 */
public class ResultModel {
    // 数据列表
    private List<Report> reportList;
    // 报告总数
    private long reportCount;
    // 总页数
    private long pageCount;
    // 当前页
    private long curPage;

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public long getReportCount() {
        return reportCount;
    }

    public void setReportCount(long reportCount) {
        this.reportCount = reportCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getCurPage() {
        return curPage;
    }

    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }
}
