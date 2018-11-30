package tx.bxp.entity;

import java.util.Date;

/**
 * @program: tx.framework
 * @description:短信验证码
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
public class SmsValidate {

    private Integer id;
    private String phone;
    private String validate;
    private Date createdate;

    public SmsValidate() {
    }

    public SmsValidate(Integer id, String phone, String validate, Date createdate) {
        this.id = id;
        this.phone = phone;
        this.validate = validate;
        this.createdate = createdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
