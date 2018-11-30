package tx.bxp.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-09-28 16:19
 **/
public class MyPTask {


    private Integer  awid;  //任务id
    private Integer projcode;//案卷Id
    private String mome;//任务内容
    private Date gatherdate;//采集时间
    private Integer worktime;//抢单时限流
    private String address;//地址
    private BigDecimal money;//金额
    private Integer score;//积分
    private String fid;//坐标
    private Integer fstate;//
    private Integer ismake;//是否完成
    private String prjimg;//处理前的图片
    private String videour;//处理前的视频
    private String manageurl;//处理后的图片
    private String managevide;//处理后的视频
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getPrjimg() {
        return prjimg;
    }

    public void setPrjimg(String prjimg) {
        this.prjimg = prjimg;
    }

    public String getVideour() {
        return videour;
    }

    public void setVideour(String videour) {
        this.videour = videour;
    }

    public String getManageurl() {
        return manageurl;
    }

    public void setManageurl(String manageurl) {
        this.manageurl = manageurl;
    }

    public String getManagevide() {
        return managevide;
    }

    public void setManagevide(String managevide) {
        this.managevide = managevide;
    }

    public Integer getAwid() { return awid; }
    public void setAwid(Integer awid) {
        this.awid = awid;
    }

    public Integer getProjcode() {
        return projcode;
    }

    public void setProjcode(Integer projcode) {
        this.projcode = projcode;
    }

    public Date getGatherdate() {
        return gatherdate;
    }

    public void setGatherdate(Date gatherdate) {
        this.gatherdate = gatherdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getMome() {
        return mome;
    }

    public void setMome(String mome) {
        this.mome = mome;
    }

    public Integer getWorktime() {
        return worktime;
    }

    public void setWorktime(Integer worktime) {
        this.worktime = worktime;
    }

    public Integer getFstate() {
        return fstate;
    }

    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    public Integer getIsmake() {
        return ismake;
    }

    public void setIsmake(Integer ismake) {
        this.ismake = ismake;
    }
}
