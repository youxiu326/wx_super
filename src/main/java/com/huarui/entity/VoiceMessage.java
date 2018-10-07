package com.huarui.entity;

/**
 * Created by lihui on 2018/10/5.
 * 语言消息
 */
public class VoiceMessage extends BaseMessage{

    private String MediaId;

    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
