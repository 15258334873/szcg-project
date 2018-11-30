package tx.bxp.dto;

import java.util.Date;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-07-24 13:38
 **/
public class BxpProjectList {

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

    public BxpProjectList() {
    }

}
