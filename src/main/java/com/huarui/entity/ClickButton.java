package com.huarui.entity;

/**
 * Created by lihui on 2018/10/20.
 * click类型的按钮
 */
public class ClickButton extends Button{
    // click类型按钮的唯一标识符
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
