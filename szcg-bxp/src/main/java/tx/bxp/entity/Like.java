package tx.bxp.entity;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:26
 **/
public class Like {

    private Integer pid;
    private Integer userid;
    private Integer type;

    public Like() {
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Like{" +
                "pid=" + pid +
                ", userid=" + userid +
                ", type=" + type +
                '}';
    }
}
