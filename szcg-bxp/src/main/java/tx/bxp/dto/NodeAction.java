package tx.bxp.dto;

/**
 * @program: szcg-project
 * @description:
 * @author:pck
 * @create: 2018-10-09 10:31
 **/
public class NodeAction {

    private  Integer id;
    private Integer actionId;
    private Integer flowId;
    private String actionName;
    private String displayName;
    private String NodeId;
    private String nextNodeId;
    private Byte isDeleted;
    private Integer limitTime;
    private Integer limitTimeType;
    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNodeId() {
        return NodeId;
    }

    public void setNodeId(String nodeId) {
        NodeId = nodeId;
    }

    public String getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(String nextNodeId) {
        this.nextNodeId = nextNodeId;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

    public Integer getLimitTimeType() {
        return limitTimeType;
    }

    public void setLimitTimeType(Integer limitTimeType) {
        this.limitTimeType = limitTimeType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "NodeAction{" +
                "id=" + id +
                ", actionId=" + actionId +
                ", flowId=" + flowId +
                ", actionName='" + actionName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", NodeId='" + NodeId + '\'' +
                ", nextNodeId='" + nextNodeId + '\'' +
                ", isDeleted=" + isDeleted +
                ", limitTime=" + limitTime +
                ", limitTimeType=" + limitTimeType +
                ", memo='" + memo + '\'' +
                '}';
    }
}
