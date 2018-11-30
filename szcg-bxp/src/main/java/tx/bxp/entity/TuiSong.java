package tx.bxp.entity;



/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class TuiSong {

    private Integer id;
    private Integer tid;
    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public TuiSong(Integer id, Integer tid, Integer flag) {
        this.id = id;
        this.tid = tid;
        this.flag = flag;
    }

    public TuiSong() {
    }
}
