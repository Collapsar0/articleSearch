package top.erhuoduoduo.entity;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 主题
 * @author: collapsar
 * @create: 2022/03/12 01:53
 */
public class Theme {
    private Integer id;

    private String themeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
}
