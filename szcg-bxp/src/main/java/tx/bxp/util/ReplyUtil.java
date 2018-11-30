package tx.bxp.util;

import tx.bxp.dto.Preply;
import tx.bxp.entity.Reply;

import java.util.ArrayList;

/**
 * @program: szcg-project
 * @description: 回复工具类
 * @author:pck
 * @create: 2018-09-21 16:19
 **/
public class ReplyUtil {

    private Integer replynumber;

    private ArrayList<Preply> preplies;

    public ArrayList<Preply> getPreplies() {
        return preplies;
    }

    public void setPreplies(ArrayList<Preply> preplies) {
        this.preplies = preplies;
    }

    public Integer getReplynumber() {
        return replynumber;
    }

    public void setReplynumber(Integer replynumber) {
        this.replynumber = replynumber;
    }
}
