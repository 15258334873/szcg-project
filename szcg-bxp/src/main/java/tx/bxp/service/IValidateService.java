package tx.bxp.service;

import tx.bxp.entity.SmsValidate;

/**
 * @program: szcg-project
 * @description: 短信验证码
 * @author:pck
 * @create: 2018-08-08 14:56
 **/
public interface IValidateService {

    public boolean isValidatePhone(String Phone);

    public void addValidate(String Phone,String validate) throws Exception;

    public void editValidate(String Phone,String validate) throws Exception;

    public String getValidateByPhone (String Phone);



}
