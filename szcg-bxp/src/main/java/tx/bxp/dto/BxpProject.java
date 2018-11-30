package tx.bxp.dto;

import java.util.Date;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-07-24 13:38
 **/
public class BxpProject {


    private Integer id;
    private String mome;
    private Date CreationTime;
    private String Fid;
    private String Address;
    private String imgurl;
    private String audiourl;
    private String videourl;
    private Integer browsenumber;
    private Integer likenumber;
    private Integer type;
    private Integer islike;
    private Integer iscollection;
    private String nickname;
    private String photo;
    private String title;
    private Integer replynumber;


    public Integer getReplynumber() {
        return replynumber;
    }

    public void setReplynumber(Integer replynumber) {
        this.replynumber = replynumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMome() {
        return mome;
    }

    public void setMome(String mome) {
        this.mome = mome;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIslike() {
        return islike;
    }

    public void setIslike(Integer islike) {
        this.islike = islike;
    }

    public Integer getIscollection() {
        return iscollection;
    }

    public void setIscollection(Integer iscollection) {
        this.iscollection = iscollection;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
