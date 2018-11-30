package tx.bxp.util;

import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-09-14 09:09
 **/
public class PushUtil {


    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("VXSFG"))
                .setNotification(Notification.android("123", "456", null))
                .build();
    }

}
