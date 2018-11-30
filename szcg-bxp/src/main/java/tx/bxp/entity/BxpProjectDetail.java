package tx.bxp.entity;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-07-12 09:45
 **/
public class BxpProjectDetail {

    //主键
    private Integer id;
    //案件CODE
    private Integer projCode;
    //案卷名称
    private String projName;
    //案卷编号
    private String projNum;
    //问题描述
    private String projDesc;
    //事部件名称
    private String className;
    //大类名称
    private String bigClassName;
    //小类名称
    private String smallClassName;
    //小类子类名称
    private String child_mallClassName;
    //区域名称
    private String areaName;
    //街道名称
    private String streetName;
    //社区名称
    private String commName;
    //单位风格名称
    private String gridName;
    //责任网格名称
    private String workGridName;
    //坐标
    private String fid;
    //部件ID
    private String partId;
    //发送部门名称
    private String targetDepartName;
    //处理部门名称
    private String doDepartName;
    //事发位置
    private String address;
    //创建时间
    private String creationTime;
    //创建人Id
    private Integer creatorUserId;

    //问题描述
    private String devicesign;


    public String getDevicesign() {
        return devicesign;
    }

    public void setDevicesign(String devicesign) {
        this.devicesign = devicesign;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjCode() {
        return projCode;
    }

    public void setProjCode(Integer projCode) {
        this.projCode = projCode;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjNum() {
        return projNum;
    }

    public void setProjNum(String projNum) {
        this.projNum = projNum;
    }

    public String getPronDesc() {
        return projDesc;
    }

    public void setProjDesc(String projDesc) {
        this.projDesc = projDesc;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBigClassName() {
        return bigClassName;
    }

    public void setBigClassName(String bigClassName) {
        this.bigClassName = bigClassName;
    }

    public String getSmallClassName() {
        return smallClassName;
    }

    public void setSmallClassName(String smallClassName) {
        this.smallClassName = smallClassName;
    }

    public String getChild_mallClassName() {
        return child_mallClassName;
    }

    public void setChild_mallClassName(String child_mallClassName) {
        this.child_mallClassName = child_mallClassName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public String getWorkGridName() {
        return workGridName;
    }

    public void setWorkGridName(String workGridName) {
        this.workGridName = workGridName;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getTargetDepartName() {
        return targetDepartName;
    }

    public void setTargetDepartName(String targetDepartName) {
        this.targetDepartName = targetDepartName;
    }

    public String getDoDepartName() {
        return doDepartName;
    }

    public void setDoDepartName(String doDepartName) {
        this.doDepartName = doDepartName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
    }
}
