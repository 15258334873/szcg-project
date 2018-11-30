package tx.bxp.entity;

import java.util.Date;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-09-27 15:34
 **/
public class sysarea {

    private Integer id;
    private String areaname;
    private Date cudateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Date getCudateDate() {
        return cudateDate;
    }

    public void setCudateDate(Date cudateDate) {
        this.cudateDate = cudateDate;
    }
}
