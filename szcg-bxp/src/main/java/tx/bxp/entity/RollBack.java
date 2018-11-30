package tx.bxp.entity;

import java.util.Date;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class RollBack {

    private Integer id;
    private Integer pid;
    private Integer proj_award_Id;
    private Integer projCode;
    private Date cudate;
    private String cause;
    private Integer idaccomplish;
    private Integer type;

    public RollBack() {
    }

    public RollBack(Integer id, Integer pid, Integer proj_award_Id, Integer projCode, Date cudate, String cause, Integer idaccomplish, Integer type) {
        this.id = id;
        this.pid = pid;
        this.proj_award_Id = proj_award_Id;
        this.projCode = projCode;
        this.cudate = cudate;
        this.cause = cause;
        this.idaccomplish = idaccomplish;
        this.type = type;
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

    public Integer getProj_award_Id() {
        return proj_award_Id;
    }

    public void setProj_award_Id(Integer proj_award_Id) {
        this.proj_award_Id = proj_award_Id;
    }

    public Integer getProjCode() {
        return projCode;
    }

    public void setProjCode(Integer projCode) {
        this.projCode = projCode;
    }

    public Date getCudate() {
        return cudate;
    }

    public void setCudate(Date cudate) {
        this.cudate = cudate;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Integer getIdaccomplish() {
        return idaccomplish;
    }

    public void setIdaccomplish(Integer idaccomplish) {
        this.idaccomplish = idaccomplish;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
