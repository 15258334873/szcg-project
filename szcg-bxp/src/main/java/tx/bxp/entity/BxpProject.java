package tx.bxp.entity;

import java.util.Date;

/**
 * @program: szcg-project
 * @description: 案件表
 * @author:pck
 * @create: 2018-07-10 09:32
 **/
public class BxpProject {

    private Integer ProjCode; //案卷主键
    private String ProjName;//案卷标识点
    private String NodeId;//当前结点
    private Date StepDate;//当前处理时间
    private String TargetDepartCode;//监督指挥中心发送部门
    private String DoDepartCode;//处理部门
    private Date StartDate; //案卷开始时间
    private Date EndDate; //案卷结束时间
    private String TelePhonist; //接线员
    private Integer TelePhonistCode; //接线员编号
    private Integer ProjSource; //问题来源对象
    private Integer ProjSourceWay; //问题来源途径 0
    private Integer ClassType; //事部件类型
    private String BigClassCode; //大类编号
    private String SmallClassCode; //小类编号
    private String ChildSmallClassCode; //小类子类编号
    private String AreaCode; //区域编码
    private String StreetCode;//街道编码
    private String CommCode;//社区编码
    private String Grid;//单元网格
    private String WorkGrid;//责任网格
    private Integer LockUserCode;//锁住用户
    private Date LockTime;//锁住时间
    private Integer ISNeedFeedBack;//是否需要回退
    private Integer IsFeedBack;//是否已回退
    private Integer IsReturn;//是否回退
    private Integer IsInspect;//是否督办
    private Integer IsDeleted;//是否删除
    private Integer sourcesystem;//来源系统 1
    private Date DeletionTime;//删除时间
    private Integer DeleterUserId;//删除用户Id
    private Date CreationTime;//创建时间
    private Integer CreatorUserId;//创建用户


    public Integer getProjCode() {
        return ProjCode;
    }

    public void setProjCode(Integer ProjCode) {
        ProjCode = ProjCode;
    }

    public String getProjName() {
        return ProjName;
    }

    public void setProjName(String projName) {
        ProjName = projName;
    }

    public String getNodeId() {
        return NodeId;
    }

    public void setNodeId(String nodeId) {
        NodeId = nodeId;
    }

    public Date getStepDate() {
        return StepDate;
    }

    public void setStepDate(Date stepDate) {
        StepDate = stepDate;
    }

    public String getTargetDepartCode() {
        return TargetDepartCode;
    }

    public void setTargetDepartCode(String targetDepartCode) {
        TargetDepartCode = targetDepartCode;
    }

    public String getDoDepartCode() {
        return DoDepartCode;
    }

    public void setDoDepartCode(String doDepartCode) {
        DoDepartCode = doDepartCode;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getTelePhonist() {
        return TelePhonist;
    }

    public void setTelePhonist(String telePhonist) {
        TelePhonist = telePhonist;
    }

    public Integer getTelePhonistCode() {
        return TelePhonistCode;
    }

    public void setTelePhonistCode(Integer telePhonistCode) {
        TelePhonistCode = telePhonistCode;
    }

    public Integer getProjSource() {
        return ProjSource;
    }

    public void setProjSource(Integer projSource) {
        ProjSource = projSource;
    }

    public Integer getProjSourceWay() {
        return ProjSourceWay;
    }

    public void setProjSourceWay(Integer projSourceWay) {
        ProjSourceWay = projSourceWay;
    }

    public Integer getClassType() {
        return ClassType;
    }

    public void setClassType(Integer classType) {
        ClassType = classType;
    }

    public String getBigClassCode() {
        return BigClassCode;
    }

    public void setBigClassCode(String bigClassCode) {
        BigClassCode = bigClassCode;
    }

    public String getSmallClassCode() {
        return SmallClassCode;
    }

    public void setSmallClassCode(String smallClassCode) {
        SmallClassCode = smallClassCode;
    }

    public String getChildSmallClassCode() {
        return ChildSmallClassCode;
    }

    public void setChildSmallClassCode(String childSmallClassCode) {
        ChildSmallClassCode = childSmallClassCode;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getStreetCode() {
        return StreetCode;
    }

    public void setStreetCode(String streetCode) {
        StreetCode = streetCode;
    }

    public String getCommCode() {
        return CommCode;
    }

    public void setCommCode(String commCode) {
        CommCode = commCode;
    }

    public String getGrid() {
        return Grid;
    }

    public void setGrid(String grid) {
        Grid = grid;
    }

    public String getWorkGrid() {
        return WorkGrid;
    }

    public void setWorkGrid(String workGrid) {
        WorkGrid = workGrid;
    }

    public Integer getLockUserCode() {
        return LockUserCode;
    }

    public void setLockUserCode(Integer lockUserCode) {
        LockUserCode = lockUserCode;
    }

    public Date getLockTime() {
        return LockTime;
    }

    public void setLockTime(Date lockTime) {
        LockTime = lockTime;
    }

    public Integer getISNeedFeedBack() {
        return ISNeedFeedBack;
    }

    public void setISNeedFeedBack(Integer ISNeedFeedBack) {
        this.ISNeedFeedBack = ISNeedFeedBack;
    }

    public Integer getIsFeedBack() {
        return IsFeedBack;
    }

    public void setIsFeedBack(Integer isFeedBack) {
        IsFeedBack = isFeedBack;
    }

    public Integer getIsReturn() {
        return IsReturn;
    }

    public void setIsReturn(Integer isReturn) {
        IsReturn = isReturn;
    }

    public Integer getIsInspect() {
        return IsInspect;
    }

    public void setIsInspect(Integer isInspect) {
        IsInspect = isInspect;
    }

    public Integer getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        IsDeleted = isDeleted;
    }

    public Integer getSourcesystem() {
        return sourcesystem;
    }

    public void setSourcesystem(Integer sourcesystem) {
        this.sourcesystem = sourcesystem;
    }

    public Date getDeletionTime() {
        return DeletionTime;
    }

    public void setDeletionTime(Date deletionTime) {
        DeletionTime = deletionTime;
    }

    public Integer getDeleterUserId() {
        return DeleterUserId;
    }

    public void setDeleterUserId(Integer deleterUserId) {
        DeleterUserId = deleterUserId;
    }

    public Date getCreationTime() {
        return CreationTime;
    }

    public void setCreationTime(Date creationTime) {
        CreationTime = creationTime;
    }

    public Integer getCreatorUserId() {
        return CreatorUserId;
    }

    public void
    setCreatorUserId(Integer creatorUserId) {
        CreatorUserId = creatorUserId;
    }
}
