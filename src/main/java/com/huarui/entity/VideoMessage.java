package com.huarui.entity;

/**
 * Created by lihui on 2018/10/5.
 * 视频消息
 */
public class VideoMessage extends BaseMessage {

    private String mediaId;
    private String thumbMediaId;
    public String getMediaId() {
        return mediaId;
    }
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    public String getThumbMediaId() {
        return thumbMediaId;
    }
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

}
