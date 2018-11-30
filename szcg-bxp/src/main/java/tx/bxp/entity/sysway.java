package tx.bxp.entity;

import java.util.Date;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-09-27 15:36
 **/
public class sysway {

    private Integer id;
    private String wayname;
    private Date cudate;
    private Integer areaid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWayname() {
        return wayname;
    }

    public void setWayname(String wayname) {
        this.wayname = wayname;
    }

    public Date getCudate() {
        return cudate;
    }

    public void setCudate(Date cudate) {
        this.cudate = cudate;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }
}
