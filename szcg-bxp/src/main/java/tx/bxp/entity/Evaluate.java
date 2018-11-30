package tx.bxp.entity;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:26
 **/
public class Evaluate {

    private Integer id;
    private Integer userid;
    private String  department;
    private Float score;
    private Integer projid;

    public Evaluate() {
    }

    public Evaluate(Integer id, Integer userid, String department, Float score, Integer projid) {
        this.id = id;
        this.userid = userid;
        this.department = department;
        this.score = score;
        this.projid = projid;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getProjid() {
        return projid;
    }

    public void setProjid(Integer projid) {
        this.projid = projid;
    }

}
