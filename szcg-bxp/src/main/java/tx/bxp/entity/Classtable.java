package tx.bxp.entity;

import java.util.Date;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:25
 **/
public class Classtable {

    private Integer id;
    private Integer parentid;
    private  String classname;
    private Integer isdel;
    private Integer ordercode;
    private Date cudate;

    public Classtable() {
    }

    public Classtable(Integer id, Integer parentid, String classname, Integer isdel, Integer ordercode, Date cudate) {
        this.id = id;
        this.parentid = parentid;
        this.classname = classname;
        this.isdel = isdel;
        this.ordercode = ordercode;
        this.cudate = cudate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(Integer ordercode) {
        this.ordercode = ordercode;
    }

    public Date getCudate() {
        return cudate;
    }

    public void setCudate(Date cudate) {
        this.cudate = cudate;
    }
}
