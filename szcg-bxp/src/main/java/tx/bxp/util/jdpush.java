package tx.bxp.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: szcg-project
 * @description:极光推送工具类
 * @author:pck
 * @create: 2018-10-10 10:06
 **/
public class jdpush {

    //极光推送>>Android
    //Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
    public static void jpushAndroidtag(Map<String, Object> parm,String Flag,String Channelid) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("returnObj",parm);
        map.put("returnFlag",Flag);
        map.put("returnStr","你有一条新消息");
        String json = JSON.toJSONString(map);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject);
     /*   String appKey = "7ccd2760080263bb06af8dc1";
        String masterSecret = "1f644328756036c80e99eaff";
*/
        String appKey = "691606927c32ff27f54a92a4";
        String masterSecret = "b4dd2cb906933debd5f25174";
        //创建JPushClient(极光推送的实例)
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        //推送的关键,构造一个payload
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                .setAudience(Audience.tag(Channelid))//你项目中的所有用户
                .setNotification(Notification.android(jsonObject.toString(), "你有一条新消息", new HashMap<String,String>()))
                //发送内容,这里不要盲目复制粘贴,这里是我从controller层中拿过来的参数)
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                //这里是指定开发环境,不用设置也没关系
                .setMessage(Message.content(jsonObject.toString()))//自定义信息
                .build();
        try {
            PushResult pu = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    //极光推送>>ios
    //Map<String, String> parm是我自己传过来的参数,同学们可以自定义参数
    public static  void jpushIOS(Map<String, String> parm,String Flag,String Channelid){

    }


    public static void jpushAndroidaAll(Map<String, Object> parm,String Flag) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("returnObj",parm);
        map.put("returnFlag",Flag);
        map.put("returnStr","你有一条新消息");
        String json = JSON.toJSONString(map);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject);
     /*   String appKey = "7ccd2760080263bb06af8dc1";
        String masterSecret = "1f644328756036c80e99eaff";
*/
        String appKey = "691606927c32ff27f54a92a4";
        String masterSecret = "b4dd2cb906933debd5f25174";
        //创建JPushClient(极光推送的实例)
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        //推送的关键,构造一个payload
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                .setAudience(Audience.all())//你项目中的所有用户
                .setNotification(Notification.android(jsonObject.toString(), "你有一条新消息", new HashMap<String,String>()))
                //发送内容,这里不要盲目复制粘贴,这里是我从controller层中拿过来的参数)
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                //这里是指定开发环境,不用设置也没关系
                .setMessage(Message.content(jsonObject.toString()))//自定义信息
                .build();
        try {
            PushResult pu = jpushClient.sendPush(payload);
            System.out.println(pu.getResponseCode());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }
    //极光推送全推
    public static  void jpushAndroid_IOS(Map<String, Object> parm,String Flag){

        jpushAndroidaAll(parm,Flag);
    }


}
