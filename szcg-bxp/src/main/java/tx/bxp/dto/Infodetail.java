package tx.bxp.dto;

import java.util.Date;

public class Infodetail {
    private  Integer id;
    private  String  title;
    private  String  content;
    private  Integer infotype;
    private  Integer browse_number;
    private  Integer like_number;
    private  String  imgurl;
    private  String audiourl;
    private  String  videourl;
    private  String nickname;
    private  String photo;
    private  Date creat_time;
    private  Integer islike;
    private  Integer collectionstatus;

    public  Infodetail(){
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Date getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Date creat_time) {
        this.creat_time = creat_time;
    }

    public Integer getBrowse_number() {
        return browse_number;
    }

    public void setBrowse_number(Integer browse_number) {
        this.browse_number = browse_number;
    }

    public Integer getLike_number() {
        return like_number;
    }

    public void setLike_number(Integer like_number) {
        this.like_number = like_number;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public Integer getInfotype() {
        return infotype;
    }

    public void setInfotype(Integer infotype) {
        this.infotype = infotype;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public Integer getCollectionstatus() {
        return collectionstatus;
    }

    public void setCollectionstatus(Integer collectionstatus) {
        this.collectionstatus = collectionstatus;
    }

    public Integer getIslike() {
        return islike;
    }

    public void setIslike(Integer islike) {
        this.islike = islike;
    }
}



