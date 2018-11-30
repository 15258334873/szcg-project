package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.entity.SmsValidate;

/**
 * @program: szcg-project
 * @description: 短信验证码
 * @author:pck
 * @create: 2018-08-08 14:57
 **/

@Mapper
public interface ValidateMapper {

    public void InsertValidate (SmsValidate smsValidate);

    public void UpdateValidate (SmsValidate smsValidate);

    public String SelectValidateByPhone (String Phone);

    public String SelectValidatePhone (String Phone);


}
