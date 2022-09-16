package top.erhuoduoduo.entity;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 标签
 * @author: collapsar
 * @create: 2022/03/12 01:53
 */
public class Tags {
    private Integer id;

    private String tagName;

    private String belong2Theme;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getBelong2Theme() {
        return belong2Theme;
    }

    public void setBelong2Theme(String belong2Theme) {
        this.belong2Theme = belong2Theme;
    }
}
