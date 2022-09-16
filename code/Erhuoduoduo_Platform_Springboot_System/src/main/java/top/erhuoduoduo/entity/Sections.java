package top.erhuoduoduo.entity;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 版块实体
 * @author: collapsar
 * @create: 2022/03/12 00:09
 */
public class Sections {
    private Integer id;

    private String sectionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
