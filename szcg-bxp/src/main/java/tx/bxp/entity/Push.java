package tx.bxp.entity;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class Push {

    private Integer id;
    private Integer userid;
    private String channelid;
    private String flag;

    public Push() {
    }

    public Push(Integer id, Integer userid, String channelid, String flag) {
        this.id = id;
        this.userid = userid;
        this.channelid = channelid;
        this.flag = flag;
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

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
