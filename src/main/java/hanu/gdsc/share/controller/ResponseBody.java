package hanu.gdsc.share.controller;

public class ResponseBody {
    public String message;
    public Object data;

    public ResponseBody(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseBody(String message) {
        this.message = message;
    }
}
