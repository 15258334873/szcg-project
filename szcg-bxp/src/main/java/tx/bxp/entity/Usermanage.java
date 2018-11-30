package tx.bxp.entity;

/**
 * @program: tx.framework
 * @description: 用户管理
 * @author:fyz
 * @create: 2018-07-04 09:22
 **/
public class Usermanage {
    private  Integer id;
    private String password;
    private String paypassword;
    private String photo;

    public Usermanage(Integer id,String password, String paypassword, String photo) {
        this.id=id;
        this.password = password;
        this.paypassword = paypassword;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
