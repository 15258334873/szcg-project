package tx.bxp.entity;

import java.util.Date;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-10-22 11:11
 **/
public class ProjectTrace {


    private Integer id;
    private Integer projcode;
    private String prenodeid;
    private String currentnodeid;
    private Integer actionid;
    private String actionname;
    private Integer usercode;
    private Date creationtime;
    private String departcode;
    private String opinion;
    private Integer isreturn;
    private Integer istimeout;
    private String memo;
    private Integer roleid;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

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

    public String getPrenodeid() {
        return prenodeid;
    }

    public void setPrenodeid(String prenodeid) {
        this.prenodeid = prenodeid;
    }

    public String getCurrentnodeid() {
        return currentnodeid;
    }

    public void setCurrentnodeid(String currentnodeid) {
        this.currentnodeid = currentnodeid;
    }

    public Integer getActionid() {
        return actionid;
    }

    public void setActionid(Integer actionid) {
        this.actionid = actionid;
    }

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public Integer getUsercode() {
        return usercode;
    }

    public void setUsercode(Integer usercode) {
        this.usercode = usercode;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public String getDepartcode() {
        return departcode;
    }

    public void setDepartcode(String departcode) {
        this.departcode = departcode;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getIsreturn() {
        return isreturn;
    }

    public void setIsreturn(Integer isreturn) {
        this.isreturn = isreturn;
    }

    public Integer getIstimeout() {
        return istimeout;
    }

    public void setIstimeout(Integer istimeout) {
        this.istimeout = istimeout;
    }
}
