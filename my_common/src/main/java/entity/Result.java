package entity;

import lombok.Data;

@Data
public class Result {
    private boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {
    }

    public boolean isFlag() {
        return flag;
    }

    public static Result success() {
        return new Result(true, StatusCode.OK, "执行成功");
    }

    public static Result failure(String msg) {
        return new Result(false, StatusCode.ERROR, msg);
    }
}
