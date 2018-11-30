package tx.bxp.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: szcg-project
 * @description: 抢单任务表
 * @author:pck
 * @create: 2018-09-11 11:31
 **/
public class PTask {

    private Integer  id;
    private Integer projcode;
    private String  taskdescribe;
    private Date gatherdate;
    private String replycontext;
    private Date cudate;
    private Integer roblimit;
    private Date robdate;
    private Integer replylimit;
    private Integer IsTimeOut;
    private Integer Isfinish;
    private Integer userid;
    private String address;
    private BigDecimal money;
    private Integer score;
    private String fid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjcode() {
        return projcode;
    }

    public void setProjcode(Integer projcode) {
        this.projcode = projcode;
    }

    public String getTaskdescribe() {
        return taskdescribe;
    }

    public void setTaskdescribe(String taskdescribe) {
        this.taskdescribe = taskdescribe;
    }

    public Date getGatherdate() {
        return gatherdate;
    }

    public void setGatherdate(Date gatherdate) {
        this.gatherdate = gatherdate;
    }

    public String getReplycontext() {
        return replycontext;
    }

    public void setReplycontext(String replycontext) {
        this.replycontext = replycontext;
    }

    public Date getCudate() {
        return cudate;
    }

    public void setCudate(Date cudate) {
        this.cudate = cudate;
    }

    public Integer getRoblimit() {
        return roblimit;
    }

    public void setRoblimit(Integer roblimit) {
        this.roblimit = roblimit;
    }

    public Date getRobdate() {
        return robdate;
    }

    public void setRobdate(Date robdate) {
        this.robdate = robdate;
    }

    public Integer getReplylimit() {
        return replylimit;
    }

    public void setReplylimit(Integer replylimit) {
        this.replylimit = replylimit;
    }

    public Integer getIsTimeOut() {
        return IsTimeOut;
    }

    public void setIsTimeOut(Integer isTimeOut) {
        IsTimeOut = isTimeOut;
    }

    public Integer getIsfinish() {
        return Isfinish;
    }

    public void setIsfinish(Integer isfinish) {
        Isfinish = isfinish;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}
