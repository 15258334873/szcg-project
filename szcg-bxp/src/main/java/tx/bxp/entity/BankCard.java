package tx.bxp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: tx.framework
 * @description: 银行卡绑定
 * @author: FYZ
 * @create: 2018-07-02 9:30
 **/
public class BankCard {

    private Integer id;
    private String username;
    private String bankname;
    private String banknum;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cudate;
    private Integer userid;
    private Integer cardtype;

    public BankCard() {
    }

    public BankCard(Integer id, String username, String bankname, String banknum, Date cudate, Integer userid,Integer cardtype) {
        this.id = id;
        this.username = username;
        this.bankname = bankname;
        this.banknum = banknum;
        this.cudate = cudate;
        this.userid = userid;
        this.cardtype=cardtype;
    }

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

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBanknum() {
        return banknum;
    }

    public void setBanknum(String banknum) {
        this.banknum = banknum;
    }

    public Date getCudate() {
        return cudate;
    }

    public void setCudate(Date cudate) {
        this.cudate = cudate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCardtype() {
        return cardtype;
    }

    public void setCardtype(Integer cardtype) {
        this.cardtype = cardtype;
    }
}
