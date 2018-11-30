package tx.bxp.util;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-09-06 09:08
 **/
public class SMSUtil {

    public String send(String phone,String validate) throws Exception{
        String meg = "短信验证码:" + validate + ",请勿泄露。";
        com.mtmasws.service.MasWSProxy masWSProxy = new com.mtmasws.service.MasWSProxy();

        String  ss = masWSProxy.sendMsgs("mtgycsglxxzx", "guiyangchengguan36", phone, meg, "123456", "测试", "1", "1");

        return ss;

    }




}
