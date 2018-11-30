package tx.bxp.entity;

import java.util.Date;

/**
 * 微信绑定表
 */

/**
 * @program: tx.framework
 * @description:微信绑定表
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class PWeChat {

    private Integer id;                            //主键 Id
    private Integer userid;                       //用户ID
    private String  wechat;                      //微信号
    private Date cudate;                          //时间
    private Integer type;                         //状态
    private String code;                         //随机码
    private String username;                    //用户名

    private Integer isdel;



    public PWeChat() {

    }

    public PWeChat(Integer id, Integer userid, String wechat, Date cudate, Integer type, String code, String username) {
        this.id = id;
        this.userid = userid;
        this.wechat = wechat;
        this.cudate = cudate;
        this.type = type;
        this.code = code;
        this.username = username;
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

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    @Override
    public String toString() {
        return "PWeChat{" +
                "id=" + id +
                ", userid=" + userid +
                ", webchat='" + wechat + '\'' +
                ", cudate=" + cudate +
                ", type=" + type +
                ", code='" + code + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


}
