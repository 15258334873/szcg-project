package tx.bxp.entity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @program: tx.framework
 * @description:奖励表
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class ProjectAward {
    //主键
    private Integer id;
    //用户id
    private Integer userid;
    //案件Id
    private Integer pid;
    //金额
    private BigDecimal money;
    //积分
    private Integer score;
    //时间
    private Date cudate;
    //是否领取
    private Integer flag;
    //
    private Integer projtype;
    //
    private Integer state;
    //
    private Integer taskid;


    public ProjectAward() {
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public Date getCudate() {
        return cudate;
    }

    public void setCudate(Date cudate) {
        this.cudate = cudate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getProjtype() {
        return projtype;
    }

    public void setProjtype(Integer projtype) {
        this.projtype = projtype;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }
}
