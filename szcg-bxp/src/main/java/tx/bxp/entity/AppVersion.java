package tx.bxp.entity;

import java.util.Date;

/**
 * @program: tx.framework
 * @description: app版本
 * @author:pck
 * @create: 2018-06-07 15:58
 **/
public class AppVersion {

    private Integer id;
    private String versioncode;
    private String content;
    private String url;
    private Date cudate;
    private Integer isdel;
    private Integer type;

    public AppVersion() {
    }

    public AppVersion(Integer id, String versioncode, String content, String url, Date cudate, Integer isdel, Integer type) {
        this.id = id;
        this.versioncode = versioncode;
        this.content = content;
        this.url = url;
        this.cudate = cudate;
        this.isdel = isdel;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(String versioncode) {
        this.versioncode = versioncode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
