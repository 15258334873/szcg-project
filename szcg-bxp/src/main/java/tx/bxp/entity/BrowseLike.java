package tx.bxp.entity;

/**
 * @program: szcg-project
 * @description: 浏览点赞表实体类
 * @author:pck
 * @create: 2018-08-02 09:31
 **/

public class BrowseLike {

    private Integer id;
    private Integer pid;
    private Integer browsenumber;
    private Integer likenumber;
    private Integer type;  //0:案件   1: 资讯
    private Integer replynumber;//回复数

    public Integer getReplynumber() {
        return replynumber;
    }

    public void setReplynumber(Integer replynumber) {
        this.replynumber = replynumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getBrowsenumber() {
        return browsenumber;
    }

    public void setBrowsenumber(Integer browsenumber) {
        this.browsenumber = browsenumber;
    }

    public Integer getLikenumber() {
        return likenumber;
    }

    public void setLikenumber(Integer likenumber) {
        this.likenumber = likenumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
