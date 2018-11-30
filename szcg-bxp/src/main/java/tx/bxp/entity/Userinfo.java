
package tx.bxp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tx.framework.security.support.UserBean;

import java.util.Date;

/**
 * @program: tx.framework
 * @description: 用户管理
 * @author:fyz
 * @create: 2018-06-28 09:22
 **/
public class Userinfo implements UserBean{

    private Integer id;
    private String loginname;
    private String password;
    private String paypassword;
    private String nickname;
    private Integer sex;
    private String photo;
    private Date registerdate;
    private Date lastlogindate;
    private Integer isdel;
    private String wechat;
    private String address;
    private String email;
    private Integer gradeid;
    private Integer integral;
    private Integer cityid;
    private Integer islogin;
    private String  version;
    private String  unionid;
    private String  type;
    private String channelid;
    private String flag;

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Userinfo() {
    }

    public Integer getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return loginname;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public Date getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGradeid() {
        return gradeid;
    }

    public void setGradeid(Integer gradeid) {
        this.gradeid = gradeid;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public Integer getIslogin() {
        return islogin;
    }

    public void setIslogin(Integer islogin) {
        this.islogin = islogin;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}