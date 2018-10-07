package com.huarui.entity;

/**
 * Created by lihui on 2018/10/5.
 * 链接消息
 */
public class LinkMessage extends BaseMessage{

    private String Title;

    private String Description;

    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
