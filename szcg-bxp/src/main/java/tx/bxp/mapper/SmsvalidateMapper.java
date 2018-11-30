package tx.bxp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tx.bxp.entity.SmsValidate;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:27
 **/
@Mapper
public interface SmsvalidateMapper {

    /**
     * 添加验证码
     * @Title: insertSmsvalidate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param smsvalidate
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    Integer insertSmsvalidate(SmsValidate smsvalidate);

    /**
     * 更新验证码
     * @Title: updateSmsvalidate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param smsvalidate
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    Integer updateSmsvalidate(SmsValidate smsvalidate);

    /**
     * 查看验证码
     * @Title: selectSmsvalidateByPhone
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param phone
     * @param @return    设定文件
     * @return Smsvalidate    返回类型
     * @throws
     */
    SmsValidate selectSmsvalidateByPhone(String phone);

    SmsValidate selectSmsvalidateByNickName(String nickName);

    SmsValidate selectSmsvalidateByPhone1(String phone);
}
