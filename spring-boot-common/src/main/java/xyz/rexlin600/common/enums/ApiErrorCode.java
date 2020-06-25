package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author hekunlin
 */

@NoArgsConstructor
@Getter
public enum ApiErrorCode {

    /**
     * 失败
     */
    FAILED(-1L, "操作失败"),
    /**
     * 成功
     */
    SUCCESS(0L, "执行成功");

    /**
     * 编码
     */
    private long code;

    /**
     * 内容
     */
    private String msg;

    ApiErrorCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
