package tx.bxp.entity;

import java.util.Date;

/**
 * @program: tx.framework
 * @description:验证码日志
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class Smslog {

    private Integer id;
    private String phone;
    private String msg;
    private Date createtime;

    public Smslog() {
    }

    public Smslog(Integer id, String phone, String msg, Date createtime) {
        this.id = id;
        this.phone = phone;
        this.msg = msg;
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
