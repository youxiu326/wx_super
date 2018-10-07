package com.huarui.utils;

import com.huarui.entity.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihui on 2018/10/4.
 */
public class MessageUtil {

    public static final String MESSAGE_TEXT = "text";//文本消息
    public static final String MESSAGE_IMAGE = "image";//图片消息
    public static final String MESSAGE_VOICE = "voice";//语音消息
    public static final String MESSAGE_VIDEO = "video";//视频消息
    public static final String MESSAGE_LIKE = "link";//链接消息
    public static final String MESSAGE_LOCATION = "location";//地理位置消息
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";//消息关注
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";//取消关注
    public static final String MESSAGE_CLICK = "CLICK";
    public static final String MESSAGE_VIEW = "VIEW";

    /**
     * xml转map
     * @param request
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException {
        Map<String,String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for (Element e:list) {
            map.put(e.getName(),e.getText());
        }
        ins.close();
        return map;
    }

    /**
     * 对象转xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xstream = new XStream();
        xstream.alias("xml",textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 语音对象的转换成xml
     *
     */
    public static String voiceMessageToXml(VoiceMessage voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }

    /**
     * 视频对象的转换成xml
     *
     */
    public static String videoMessageToXml(VideoMessage videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }

    /**
     * 音乐对象的转换成xml
     *
     */
/*    public static String musicMessageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }*/
    /**
     * 图文对象转换成xml
     *
     */
    /*public static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }*/

    /**
     * 图片对象转换成xml
     *
     */

    public static String imageMessageToXml(ImageMessage imageMessage)
    {
        XStream xstream = new XStream();
        xstream.alias("xml",imageMessage.getClass());
        return xstream.toXML(imageMessage);

    }

    /**
     * 链接对象转换成xml
     *
     */

    public static String linkMessageToXml(LinkMessage linkMessage)
    {
        xstream.alias("xml",linkMessage.getClass());
        return xstream.toXML(linkMessage);
    }

    /**
     * 地理位置对象转换成xml
     *
     */

    public static String locationMessageToXml(LocationMessage locationMessage)
    {
        xstream.alias("xml",locationMessage.getClass());
        return xstream.toXML(locationMessage);
    }


    public static String initText(String fromUserName,String toUserName,String content){
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(fromUserName);
        textMessage.setToUserName(toUserName);
        textMessage.setMsgType(MessageUtil.MESSAGE_TEXT);
        textMessage.setCreateTime(new Date().getTime()+"");
        textMessage.setContent(content);
       return MessageUtil.textMessageToXml(textMessage);
    }

    /**
     * 主菜单栏
     * @return
     */
    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注，请按照菜单提示进行操作\n\n");
        sb.append("1.哈哈哈");
        sb.append("2.嘻嘻嘻");
        return sb.toString();
    }

    public static String firstMenu(){
        return "您点击了 哈哈哈";
    }

    public static String secondMenu(){
        return "您点击了 嘻嘻嘻";
    }

    /**
     * 拓展xstream，使得支持CDATA块
     *
     */
    private static XStream xstream = new XStream(new XppDriver(){
        public HierarchicalStreamWriter createWriter(Writer out){
            return new PrettyPrintWriter(out){
                //对所有的xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name,Class clazz){
                    super.startNode(name,clazz);
                }

                protected void writeText(QuickWriter writer, String text){
                    if(cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }else{
                        writer.write(text);
                    }
                }
            };
        }
    });
}
