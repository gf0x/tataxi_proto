package app.pojo;

/**
 * Created by Alex_Frankiv on 02.04.2017.
 */
public class AjaxResponseBody {
    private String message;
    private String code;

    @Override
    public String toString() {
        return "AjaxResponseBody{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AjaxResponseBody() {

    }

    public AjaxResponseBody(String code, String message) {
        this.message = message;
        this.code = code;
    }
}
