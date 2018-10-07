package com.huarui.controller;

import com.huarui.entity.*;
import com.huarui.utils.CheckUtil;
import com.huarui.utils.MessageUtil;
import com.huarui.utils.TulingUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by lihui on 2018/10/4.
 */
@Controller
public class WeixinController {

    @GetMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce)){
            out.print(echostr);
        }
    }

    @PostMapping("hello")
    public void message(HttpServletRequest request,HttpServletResponse response) throws IOException, DocumentException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> map = MessageUtil.xmlToMap(request);
        //发送方帐号 (主体不一样 需要转换)
        String fromUserName = map.get("ToUserName");//FromUserName
        // 开发者微信号
        String toUserName = map.get("FromUserName");//ToUserName
        String msgType = map.get("MsgType");
        String content = map.get("Content");

        PrintWriter out = response.getWriter();
        String message = null;
        //文本消息
        if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
            if ("1".equals(content)) {
                message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.firstMenu());
            } else if ("2".equals(content)) {
                message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.secondMenu());
            } else if ("?".equals(content) || "？".equals(content)) {
                message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.menuText());
            } else {
                message = MessageUtil.initText(fromUserName, toUserName, TulingUtil.hello(content));
            }
            //订阅
        } else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
            String eventType = map.get("Event");
            if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
                message = MessageUtil.initText(fromUserName, toUserName, "谢谢关注");
            }//取消订阅
            else if (MessageUtil.MESSAGE_UNSUBSCRIBE.equals(eventType)) {
                message = MessageUtil.initText(fromUserName, toUserName, "取消订阅");
            } else if (eventType.equals(MessageUtil.MESSAGE_CLICK)) {
                message = MessageUtil.initText(fromUserName, toUserName, "自定义菜单消息出来；" + content);
            }
        }//图片消息
        else if (msgType.equals(MessageUtil.MESSAGE_IMAGE)) {
            ImageMessage imageMessage = new ImageMessage();
            imageMessage.setToUserName(fromUserName);
            imageMessage.setFromUserName(toUserName);
            imageMessage.setCreateTime(new Date().getTime() + "");
            imageMessage.setMsgType(MessageUtil.MESSAGE_IMAGE);
            imageMessage.setPicUrl("http://img24.pplive.cn//2013//07//24//12103112092_230X306.jpg");
            message = MessageUtil.imageMessageToXml(imageMessage);
        }
        //地理位置
        else if (msgType.equals(MessageUtil.MESSAGE_LOCATION)) {
            LocationMessage locationMessage = new LocationMessage();
            locationMessage.setToUserName(fromUserName);
            locationMessage.setFromUserName(toUserName);
            locationMessage.setCreateTime(new Date().getTime() + "");
            locationMessage.setMsgType(MessageUtil.MESSAGE_LOCATION);
            locationMessage.setLocation_X(map.get("Location_X"));
            locationMessage.setLocation_Y(map.get("Location_Y"));
            locationMessage.setScale(map.get("Scale"));
            locationMessage.setLabel(map.get("Label"));
            message = MessageUtil.locationMessageToXml(locationMessage);
        }
        //视频消息
        else if (msgType.equals(MessageUtil.MESSAGE_VIDEO)) {
            VideoMessage videoMessage = new VideoMessage();
            videoMessage.setToUserName(fromUserName);
            videoMessage.setFromUserName(toUserName);
            videoMessage.setCreateTime(new Date().getTime() + "");
            videoMessage.setMsgType(MessageUtil.MESSAGE_VIDEO);
            videoMessage.setMediaId(map.get("MediaId"));
            videoMessage.setThumbMediaId(map.get("ThumbMediaId"));
            message = MessageUtil.videoMessageToXml(videoMessage);
        }
        //链接消息
        else if (msgType.equals(MessageUtil.MESSAGE_LIKE)) {
            LinkMessage linkMessage = new LinkMessage();
            linkMessage.setToUserName(fromUserName);
            linkMessage.setFromUserName(toUserName);
            linkMessage.setCreateTime(new Date().getTime() + "");
            linkMessage.setMsgType(MessageUtil.MESSAGE_LIKE);
            linkMessage.setTitle(map.get("Title"));
            linkMessage.setDescription(map.get("Description"));
            linkMessage.setUrl(map.get("Url"));
            message = MessageUtil.linkMessageToXml(linkMessage);
        }
        //语音消息
        else if (msgType.equals(MessageUtil.MESSAGE_VOICE)) {
            VoiceMessage voiceMessage = new VoiceMessage();
            voiceMessage.setToUserName(fromUserName);
            voiceMessage.setFromUserName(toUserName);
            voiceMessage.setCreateTime(new Date().getTime() + "");
            voiceMessage.setMsgType(MessageUtil.MESSAGE_VOICE);
            voiceMessage.setMediaId(map.get("MediaId"));
            voiceMessage.setFormat(map.get("Format"));
            message = MessageUtil.voiceMessageToXml(voiceMessage);
        }
            System.out.println(message);
            out.write(message);
            out.close();
        }
}
