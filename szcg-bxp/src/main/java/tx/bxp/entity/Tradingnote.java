package tx.bxp.entity;

import java.util.Date;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class Tradingnote {

    private Integer id;
    private String username;
    private Integer userid;
    private Date tradingTime;
    private String account;
    private String bankId;
    private String amont;
    private String type;
    private Integer pid;
    private Date handingtime;
    private Integer flag;
    private String msg;
    private  Integer state;
    private Date exceptionalTime;
    private Integer status;
    private Date checkTime;
    private Integer conditions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTradingTime() {
        return tradingTime;
    }

    public void setTradingTime(Date tradingTime) {
        this.tradingTime = tradingTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getAmont() {
        return amont;
    }

    public void setAmont(String amont) {
        this.amont = amont;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getHandingtime() {
        return handingtime;
    }

    public void setHandingtime(Date handingtime) {
        this.handingtime = handingtime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getExceptionalTime() {
        return exceptionalTime;
    }

    public void setExceptionalTime(Date exceptionalTime) {
        this.exceptionalTime = exceptionalTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getConditions() {
        return conditions;
    }

    public void setConditions(Integer conditions) {
        this.conditions = conditions;
    }
}
