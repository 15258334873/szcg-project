package tx.bxp.entity;

import java.util.Date;

/**
 * @program: tx.framework
 * @description: 银行卡或微信绑定或解绑记录表
 * @author:pck
 * @create: 2018-06-07 16:24
 **/
public class BindNote {

    private Integer id;
    private Integer userid;
    private String username;
    private String bankname;
    private String bNumber;
    private Date cudate;
    private Integer type;
    private Integer flag;
    private Integer status;
    private String msg;
    private String money;

    public BindNote() {
    }

    public BindNote(Integer id, Integer userid, String username, String bankname, String bNumber, Date cudate, Integer type, Integer flag, Integer status, String msg, String money) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.bankname = bankname;
        this.bNumber = bNumber;
        this.cudate = cudate;
        this.type = type;
        this.flag = flag;
        this.status = status;
        this.msg = msg;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getbNumber() {
        return bNumber;
    }

    public void setbNumber(String bNumber) {
        this.bNumber = bNumber;
    }

    public Date getCudate() {
        return cudate;
    }

    public void setCudate(Date cudate) {
        this.cudate = cudate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}