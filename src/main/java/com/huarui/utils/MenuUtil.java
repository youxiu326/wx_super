package com.huarui.utils;

import com.huarui.entity.Button;
import com.huarui.entity.ClickButton;
import com.huarui.entity.Menu;
import com.huarui.entity.ViewButton;

/**
 * Created by lihui on 2018/10/20.
 */
public class MenuUtil {

    /**
     * 组装自定义菜单对象
     *
     * @return
     */
    public static Menu initMenu() {
        Menu menu = new Menu();
        ClickButton clickButton = new ClickButton();
        clickButton.setName("click菜单");
        clickButton.setType("click");
        // key可自定义
        clickButton.setKey("click_001");

        ViewButton viewButton = new ViewButton();
        viewButton.setName("view菜单");
        viewButton.setType("view");
        // url需要带http协议头
        viewButton.setUrl("http://www.baidu.com");

        ClickButton clickButton2 = new ClickButton();
        clickButton2.setName("扫码事件");
        clickButton2.setType("scancode_push");
        clickButton2.setKey("click_002");

        ClickButton clickButton3 = new ClickButton();
        clickButton3.setName("发送地理位置");
        clickButton3.setType("location_select");
        clickButton3.setKey("click_003");

        Button button = new Button();
        button.setName("自定义菜单");
        // 设置子菜单
        button.setSub_button(new Button[]{clickButton2, clickButton3});

        menu.setButton(new Button[]{clickButton, viewButton, button});
        return menu;
    }

}
