package app.dto;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Alex_Frankiv on 02.04.2017.
 */
public class AjaxResponseBody {
    private String msg;
    private String code;

    @Override
    public String toString() {
        return "AjaxResponseBody{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AjaxResponseBody() {

    }

    public AjaxResponseBody(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }
}
