package tx.bxp.service;

import tx.bxp.entity.SmsValidate;

/**
 * @program: tx.framework
 * @description:
 * @author:pck
 * @create: 2018-06-07 16:49
 **/
public interface ISmsvalidateService {

    /**
     * 添加验证码
     * @Title: insertSmsvalidate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param smsvalidate
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    public Integer InsertSmsvalidate(SmsValidate smsvalidate);


    /**
     * 更新验证码
     * @Title: updateSmsvalidate
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param smsvalidate
     * @param @return    设定文件
     * @return Integer    返回类型
     * @throws
     */
    public Integer UpdateSmsvalidate(SmsValidate smsvalidate);

    /**
     * 查看验证码
     * @Title: selectSmsvalidateByPhone
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param phone
     * @param @return    设定文件
     * @return Smsvalidate    返回类型
     * @throws
     */
    public SmsValidate getSmsvalidateByPhone(String phone);

    public SmsValidate getSmsvalidate(String phone);
}
