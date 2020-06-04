package xyz.rexlin600.common.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author hekunlin
 */

@NoArgsConstructor
@Getter
public enum ApiErrorCode {

    FAILED(-1L, "操作失败"),
    SUCCESS(0L, "执行成功");

    private long code;
    private String msg;

    ApiErrorCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
