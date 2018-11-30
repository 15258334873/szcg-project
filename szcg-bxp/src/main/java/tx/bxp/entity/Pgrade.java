package tx.bxp.entity;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class Pgrade {

    private Integer id;
    private String name;
    private Integer integral;
    private String isdel;

    public Pgrade() {
    }

    public Pgrade(Integer id, String name, Integer integral, String isdel) {
        this.id = id;
        this.name = name;
        this.integral = integral;
        this.isdel = isdel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }
}
