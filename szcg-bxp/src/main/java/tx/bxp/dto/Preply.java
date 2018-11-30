package tx.bxp.dto;

import java.util.Date;

/**
 * @program: szcg-project
 * @description: 评价列表
 * @author:pck
 * @create: 2018-08-03 11:42
 **/
public class Preply {

    private Integer id;
    private Integer uid;
    private Integer pid;
    private Integer replystatus;
    private String content;
    private Date cudate;
    private String nickname;
    private String photo;
    private Integer type;
    private Integer isPraise;
    private Integer likesum;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Preply() {

    }

    public Integer getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(Integer isPraise) {
        this.isPraise = isPraise;
    }

    public Integer getLikesum() {
        return likesum;
    }

    public void setLikesum(Integer likesum) {
        this.likesum = likesum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getReplystatus() {
        return replystatus;
    }

    public void setReplystatus(Integer replystatus) {
        this.replystatus = replystatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCudate() {
        return cudate;
    }

    public void setCudate(Date cudate) {
        this.cudate = cudate;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Preply{" +
                "id=" + id +
                ", uid=" + uid +
                ", pid=" + pid +
                ", replystatus=" + replystatus +
                ", content='" + content + '\'' +
                ", cudate=" + cudate +
                ", nickname='" + nickname + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
