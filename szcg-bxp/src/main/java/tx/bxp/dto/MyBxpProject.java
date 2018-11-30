package tx.bxp.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-09-26 15:46
 **/
public class MyBxpProject {

    private Integer id;
    private Date CreationTime;
    private String Fid;
    private String Address;
    private String imgurl;
    private String audiourl;
    private String videourl;
    private Integer type;
    private Integer browsenumber;
    private Integer likenumber;
    private String title;
    private String mome;
    private  int state; // 状态
    private  long Projcode; // 案件编号
    private String  score;  //  积分
    private BigDecimal money; // 奖励金

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return CreationTime;
    }

    public void setCreationTime(Date creationTime) {
        CreationTime = creationTime;
    }

    public String getFid() {
        return Fid;
    }

    public void setFid(String fid) {
        Fid = fid;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getAudiourl() {
        return audiourl;
    }

    public void setAudiourl(String audiourl) {
        this.audiourl = audiourl;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBrowsenumber() {
        return browsenumber;
    }

    public void setBrowsenumber(Integer browsenumber) {
        this.browsenumber = browsenumber;
    }

    public Integer getLikenumber() {
        return likenumber;
    }

    public void setLikenumber(Integer likenumber) {
        this.likenumber = likenumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMome() {
        return mome;
    }

    public void setMome(String mome) {
        this.mome = mome;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getProjcode() {
        return Projcode;
    }

    public void setProjcode(long projcode) {
        Projcode = projcode;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
