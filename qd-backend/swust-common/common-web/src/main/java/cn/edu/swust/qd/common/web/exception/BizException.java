package cn.edu.swust.qd.common.web.exception;

import cn.edu.swust.qd.common.result.IResultCode;
import lombok.Getter;

/**
 * 自定义业务异常
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
@Getter
public class BizException extends RuntimeException {

    public IResultCode resultCode;

    public BizException(IResultCode errorCode) {
        super(errorCode.getMsg());
        this.resultCode = errorCode;
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }
}
