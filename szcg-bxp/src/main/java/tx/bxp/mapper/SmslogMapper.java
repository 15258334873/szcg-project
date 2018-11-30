package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import tx.bxp.entity.Smslog;

/**
 * @program: szcg-project
 * @description: 验证码日志
 * @author:pck
 * @create: 2018-08-09 09:19
 **/
@Mapper
public interface SmslogMapper {

    public void InsertSmsLog(Smslog smslog);

}
