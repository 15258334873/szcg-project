package tx.bxp.entity;

import java.util.Date;


/**
 * @program: szcg-project
 * @description: 案件表
 * @author:pck
 * @create: 2018-07-10 09:32
 **/
public class Reply {

    private Integer id;
    private Integer userid;
    private Integer projid;
    private Integer replystatus;
    private String content;
    private Date replydate;
    private Integer type;
    private String address;
    private String fid;

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

    public Integer getProjid() {
        return projid;
    }

    public void setProjid(Integer projid) {
        this.projid = projid;
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

    public Date getReplydate() {
        return replydate;
    }

    public void setReplydate(Date replydate) {
        this.replydate = replydate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }


}
