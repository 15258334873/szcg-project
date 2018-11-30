package tx.bxp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.util.EntityUtils;
import tx.bxp.entity.BindNote;
import org.apache.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @program: szcg-project
 * @description:银行卡工具类
 * @author:pck
 * @create: 2018-09-18 09:15
 **/
public class BankUtils {

    /*
     * 创建银行日志
     *
     * */
    public BindNote getBindNote(String bankname, Date date, int flag, String bnumber, int type, String username){
        BindNote bindNote = new BindNote();
        bindNote.setBankname(bankname);
        bindNote.setCudate(date);
        bindNote.setFlag(flag);
        bindNote.setbNumber(bnumber);
        bindNote.setType(type);
        bindNote.setUsername(username);
        return bindNote;
    }

    /*
     * 解析httpclient返回的json数据
     *
     * */
    public List<String> getResponseValues(HttpResponse resp) throws Exception{
        List<String> lists=new ArrayList<String>();
        String str = EntityUtils.toString(resp.getEntity(), "utf-8");
        // 把json字符串转换成json对象
        JSONObject js = JSON.parseObject(str);
        String msg=js.getString("msg");//提示信息
        String code=js.getString("code");//状态码
        String data=js.getString("data");//随机码
        lists.add(msg);
        lists.add(code);
        lists.add(data);
        return lists;
    }

}
