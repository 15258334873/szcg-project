package tx.bxp.entity;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class Pram {

    private Integer id;
    private String title;
    private String keyname;
    private String value;
    private String content;
    private String cudate;
    private Integer isdel;

    public Pram() {
    }

    public Pram(Integer id, String title, String keyname, String value, String content, String cudate, Integer isdel) {
        this.id = id;
        this.title = title;
        this.keyname = keyname;
        this.value = value;
        this.content = content;
        this.cudate = cudate;
        this.isdel = isdel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCudate() {
        return cudate;
    }

    public void setCudate(String cudate) {
        this.cudate = cudate;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}
