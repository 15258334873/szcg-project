package tx.bxp.entity;

import java.util.Date;

/**
 * 收藏列表
 * @program: tx.framework
 * @description: 用户收藏列表
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class PCollection {

    private Integer id;
    private Integer userid;
    private Integer pid;
    private Integer type;
    private Date cudate;
    private Integer isdel;

    public PCollection() {
            super();
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCudate() {
        return cudate;
    }

    public void setCudate(Date cudate) {
        this.cudate = cudate;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

}
