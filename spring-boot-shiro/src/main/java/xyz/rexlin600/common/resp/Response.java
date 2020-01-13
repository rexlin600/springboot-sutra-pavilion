package xyz.rexlin600.common.resp;

/**
 * 通用响应对象
 *
 * @author rexlin600
 */
public class Response<T> {

    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "成功";

    private int code;

    private String msg;

    private T data;

    private Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private Response() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    private Response(int code, String msg) {
        this(code, msg, null);
    }

    private Response(T data) {
        this(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> Response<T> success() {
        return new Response<>();
    }

    public static <T> Response<T> successWithData(T data) {
        return new Response<>(data);
    }

    public static <T> Response<T> failWithCodeAndMsg(int code, String msg) {
        return new Response<>(code, msg, null);
    }

    public static <T> Response<T> buildWithParam(ResponseParam param) {
        return new Response<>(param.getCode(), param.getMsg(), null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static class ResponseParam {
        private int code;
        private String msg;

        private ResponseParam(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static ResponseParam buildParam(int code, String msg) {
            return new ResponseParam(code, msg);
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
