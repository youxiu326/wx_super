package com.huarui.controller;

import com.huarui.entity.Menu;
import com.huarui.utils.MenuUtil;
import net.sf.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lihui on 2018/10/20.
 */
@Controller
public class MenuController {


    /**
     * 创建菜单
     * @return
     */
    @RequestMapping("/createMenu")
    public @ResponseBody String createMenu(){

        RestTemplate restTemplate = new RestTemplate();


        /*
         * account_token 通过appid 与 refer...查看

        * 需要公众号通过认证才能调用创建菜单接口
        * https://mp.weixin.qq.com/debug/cgi-bin/apiinfo?t=index&type=%E8%87%AA%E5%AE%9A%E4%B9%89%E8%8F%9C%E5%8D%95&form=%E8%87%AA%E5%AE%9A%E4%B9%89%E8%8F%9C%E5%8D%95%E5%88%9B%E5%BB%BA%E6%8E%A5%E5%8F%A3%20/menu/create---------------------%E4%BD%9C%E8%80%85%EF%BC%9Ayang%E6%B4%8BPHPer%E6%9D%A5%E6%BA%90%EF%BC%9ACSDN%E5%8E%9F%E6%96%87%EF%BC%9Ahttps://blog.csdn.net/qq_34341290/article/details/52734035%E7%89%88%E6%9D%83%E5%A3%B0%E6%98%8E%EF%BC%9A%E6%9C%AC%E6%96%87%E4%B8%BA%E5%8D%9A%E4%B8%BB%E5%8E%9F%E5%88%9B%E6%96%87%E7%AB%A0%EF%BC%8C%E8%BD%AC%E8%BD%BD%E8%AF%B7%E9%99%84%E4%B8%8A%E5%8D%9A%E6%96%87%E9%93%BE%E6%8E%A5%EF%BC%81
        * */

        String account_token ="14_h7fR4bPD9fLFwEFnJgyizHuYQkmQC8np87zNMocCd9DYFiinRG0a-fQXfOIRLVjB9BuRsLbGJhPrI5lmanKG3qAgYPQluvjbYcKphV-8puDzHzEpArme-OID4_wL6H0Z7W-XXh2vuV6U-PV7TLZfAHAIAO";
        String url ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=youxiu326";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();

        // 组装自定义菜单对象
        Menu menu = MenuUtil.initMenu();
        // 将对象转换成json
        String menuStr = JSONObject.fromObject(menu).toString();

        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, menuStr, JSONObject.class);
        JSONObject object = responseEntity.getBody();
        return object.toString();
    }


}
