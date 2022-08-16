package hanu.gdsc.share.controller;

import hanu.gdsc.share.error.BusinessLogicError;
import hanu.gdsc.share.error.UnauthorizedError;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerHandler {

    @Schema(title = "Response")
    public static class Result {
        @Schema(description = "specify the message of response", example = "Success", required = true)
        private String message;
        @Schema(description = "specify the data of response", example = "", required = true)
        private Object data;

        public Result(String message, Object data) {
            this.message = message;
            this.data = data;
        }
    }

    @FunctionalInterface
    public static interface Runnable {
        public Result run();
    }

    public static ResponseEntity<?> handle(Runnable runner) {
        try {
            Result result = runner.run();
            return new ResponseEntity<>(
                    new ResponseBody(result.message, result.data),
                    HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e instanceof BusinessLogicError) {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                if (e instanceof UnauthorizedError)
                    status = HttpStatus.UNAUTHORIZED;
                e.printStackTrace();
                return new ResponseEntity<>(
                        new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode(), null),
                        status
                );
            }
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
