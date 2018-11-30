package tx.czt.exception;

import tx.framework.api.exception.ApiException;

/**
 * API 异常类
 *
 * @author lilei
 * @date 2018-5-10
 */
public class BxpException extends ApiException {

    private Integer code;

    protected BxpException(Integer code, String message) {
        super(code, message);
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
