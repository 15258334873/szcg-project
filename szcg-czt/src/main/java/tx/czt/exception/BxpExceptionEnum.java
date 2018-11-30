package tx.czt.exception;

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
