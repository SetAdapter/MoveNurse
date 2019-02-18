package com.fy.entity;

import com.fy.base.BaseBean;

/**
 * 菜单dialog 列表 实体类
 * Created by fangs on 2017/3/13.
 */
public class DialogEntity extends BaseBean {

    private int menuIMG = -1;
    private String menuName = "";
    private int menuTAG = -1;

    public DialogEntity(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuIMG() {
        return menuIMG;
    }

    public void setMenuIMG(int menuIMG) {
        this.menuIMG = menuIMG;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuTAG() {
        return menuTAG;
    }

    public void setMenuTAG(int menuTAG) {
        this.menuTAG = menuTAG;
    }
}
