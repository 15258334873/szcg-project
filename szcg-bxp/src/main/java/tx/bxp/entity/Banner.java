package tx.bxp.entity;

import java.util.Date;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:23
 **/
public class Banner {
    private Integer id;
    private String bannertitle;
    private String imageurl;
    private String returnurl;
    private Date cudate;
    private Integer isdel;

    public Banner() {
    }

    public Banner(Integer id, String bannertitle, String imageurl, String returnurl, Date cudate, Integer isdel) {
        this.id = id;
        this.bannertitle = bannertitle;
        this.imageurl = imageurl;
        this.returnurl = returnurl;
        this.cudate = cudate;
        this.isdel = isdel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBannertitle() {
        return bannertitle;
    }

    public void setBannertitle(String bannertitle) {
        this.bannertitle = bannertitle;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getReturnurl() {
        return returnurl;
    }

    public void setReturnurl(String returnurl) {
        this.returnurl = returnurl;
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
