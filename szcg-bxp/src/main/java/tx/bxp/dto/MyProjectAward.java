package tx.bxp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: szcg-project
 * @description: 我的奖励列表
 * @author:pck
 * @create: 2018-09-28 10:22
 **/
public class MyProjectAward {


    private Integer id;
    private Integer userId;
    private Integer pId;
    private BigDecimal money;
    private Float score;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date cudate;
    private Integer flag;
    private Integer tasktype;
    private  String mome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
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

    public Integer getTasktype() {
        return tasktype;
    }

    public void setTasktype(Integer tasktype) {
        this.tasktype = tasktype;
    }

    public String getMome() {
        return mome;
    }

    public void setMome(String mome) {
        this.mome = mome;
    }
}
