package hanu.gdsc.share.controller;

public class ResponseBody {
    public String code;
    public String message;
    public Object data;

    public ResponseBody(String message, String code, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseBody(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseBody(String message) {
        this.message = message;
    }
}
