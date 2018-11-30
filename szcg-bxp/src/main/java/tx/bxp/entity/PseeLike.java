package tx.bxp.entity;

/**
 * @program: szcg-project
 * @description: 浏览点赞
 * @author:pck
 * @create: 2018-07-17 10:12
 **/
public class PseeLike {

    private Integer infoid;
    private Integer seenum;
    private Integer likenum;
    private Integer infostatus;

    public Integer getInfoid() {
        return infoid;
    }

    public void setInfoid(Integer infoid) {
        this.infoid = infoid;
    }

    public Integer getSeenum() {
        return seenum;
    }

    public void setSeenum(Integer seenum) {
        this.seenum = seenum;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public Integer getInfostatus() {
        return infostatus;
    }

    public void setInfostatus(Integer infostatus) {
        this.infostatus = infostatus;
    }
}
