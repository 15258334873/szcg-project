package tx.bxp.exception;

/**
 * API 异常枚举
 *
 * @author lilei
 * @date 2018-5-10
 */
public enum BxpExceptionEnum {


    /**
     * 验证码
     */
    SMS_FAILUREGO(-1, "短信验证码发送失败！"),
    USER_NOT_EXIST(-1, "用户不存在！"),
    VALIDATE_PAST(-1, "短信验证码已过期！"),
    VALIDATE_MISTAKE(-1, "短信验证码有误！"),

    /**
     * 用户模块
     */
    USERNAME_PASSWORDISEMPTY(-1, "用户名或密码为空！"),
    NICKNAME_USED(-1, "用户昵称已经被人使用！"),
    VERIFICATION_TIMEOUT(-1, "验证码超时！"),
    VERIFICATION_INCORRECTNESS(-1, "验证码不正确！"),
    NICKNAME_NOT_EXIST(-1, "用户昵称不能为空！"),
    USER_EXIST(-1, "用户已存在！"),
    UNKNOWN_ACCOUNT(-1, "用户名或密码错误！"),
    PASSWORD_IS_NAME(-1, " 新老密码相同！"),
    PASSWORD_IS_WARONG(-1, "原密码有误，请确认原密码是否正确！"),
    PAY_PASSWORD_IS_NAME(-1, "支付密码不正确！"),
    DISABLE_ACCOUNT(-1, "账号被禁用！"),
    NOT_BOUND(-1," 您还未绑定银行卡或微信，请先进行绑定"),
    PAYPASSWORD_ALREEDY_SET(-1, "支付密码已设置！"),
    PAYPASSWORD_IS_SET(-1, "支付密码未设置！"),
    USER_NO_EXIST(-1, "用户不存在！"),

    /**
     * 案件
     */
    LOGIN_DATE(-1, "请先登录后发言！"),
    VERSION_IS_TOOLOW(-1, "您当前版本过低，请更新后再上报！"),
    REPORTED_LIMIT_NUMBER(-1," 今日上报条数已过指定条数，请于明日进行上报！"),
    LOSE(-1,"该单已经失效！"),
    LOOTED(-1,"该单已被抢！"),
    TASK_NO(-1,"该任务不存在或已过期"),


    /**
     * 银行卡
     */
    BANK_BINDUNG(-1, "银行卡已绑定！"),
    DATE_ERROR(-1, "每天23点到次日1点之间,银行系统维护,不能提现,敬请谅解！"),
    BANK_ERROR(-1," 获取银行数据失败，请稍后再试！"),
    NOT_BANK(-1,"您未绑定银行卡！"),
    NOT_USER_BANK(-1,"没有此用户，不能解绑！"),
    NOT_USER_AWARD(-1,"您有奖励正在提现中,不能解绑！"),
    NOT_AWARD(-1,"该奖励不存在！"),
    WITHDRAW_DEFEATED(-1,"提现失败"),
    CANNOT_WITHDRAW(-1,"你已被禁言，不能领取任何奖励"),
    CANNOT_WITHDRAW1(-1,"该奖励不符合提现规则，不能领奖"),
    CANNOT_WITHDRAW2(-1,"该奖励已失效，不能领取奖励"),
    CANNOT_WITHDRAW3(-1,"该奖励已领取，不能再次领取奖励"),
    CANNOT_WITHDRAW4(-1,"提现中,银行系统问题,若48小时内未到账,请联系贵阳百姓拍客服"),
    BINDUNG_ERROR(-1,"您已有微信号绑定成功,不能再次绑定！"),
    BINDUNG_SUCCESS(-1,"微信号绑定成功,请微信关注(中国银行贵州分行)公众号,并发送随机码,便于您领取奖励金."),
    WECHAT_ERROR(-1,"您已绑定微信号,还未完成微信公众号关注功能，不能再次绑定,请去微信关注“中国银行贵州分行”公众号,以便领取奖励."),
    NOT_WECHAT(-1,"您未绑定微信！"),
    NOT_ATTENTION_WECHAT(-1," 您还未关注微信公众号，请先进行关注！"),
    WECHAT_ERROR2(-1,"您的微信未关注(中国银行贵州分行)公众号，不能领取奖励."),
    WECHAT_ERROR3(-1,"您的微信绑定失败，不能领取奖励，请解绑后再绑定."),
    WECHAT_ERROR4(-1,"您未绑定微信，不能领取奖励."),
    TIXIAN_ERROR(-1,"您有奖励正在提现中,不能解绑."),
    UNBANGDING_SUCCESS(-1,"解绑成功."),
    GETBANK_ERROR(-1,"获取银行数据失败，请稍后再试"),
    USER_ERROR(-1,"没有此用户，不能解绑"),
    USER_NULL(-1,"您未绑定微信，无需解绑"),
    bonus_success(-1,"微信领取奖励成功，以微信到账时间为准，请注意查收"),
    BONUS_ALREADY(-1,"该奖励已领取，不能再次领取奖励"),
    BONUS_ERROR(-1,"该奖励不符合提现规则，不能领奖"),
    BONUS_NULL(-1,"该奖励不存在"),
    USER_NOTSAME(-1,"登录的用户和奖励的用户不匹配，提现失败"),
    JINYAN_ERROR(-1,"你已被禁言，不能领取任何奖励"),
    BANK_MISTAKE(-1,"微信提现中,银行系统问题,若48小时内未到账,请联系贵阳百姓拍客服"),
    /*LOOTED(-1,"该单已被抢！"),*/



    /**
     * 全局
     */

    INCOMPLETE_INFORMATION(-1,"信息不完整！"),
   REQUEST_FEILED(-1,"请求失败，请稍后再试！");

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public BxpException exception() {
        return new BxpException(code, message);
    }

    BxpExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
