package hanu.gdsc.infrastructure.share.controller;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;
import hanu.gdsc.domain.share.exceptions.RuntimeBusinessLogicException;
import hanu.gdsc.domain.share.exceptions.UnauthorizedException;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
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
        public Result run() throws BusinessLogicException;
    }

    public static ResponseEntity<?> handle(Runnable runner) {
        try {
            Result result = runner.run();
            return new ResponseEntity<>(
                    new ResponseBody(result.message, result.data),
                    HttpStatus.OK
            );
        } catch (Throwable e) {
            if (e instanceof BusinessLogicException) {
                HttpStatus status = HttpStatus.BAD_REQUEST;
                if (e instanceof UnauthorizedException)
                    status = HttpStatus.UNAUTHORIZED;
                return new ResponseEntity<>(
                        new ResponseBody(e.getMessage(), ((BusinessLogicException) e).getCode(), null),
                        status
                );
            } else if (e instanceof RuntimeBusinessLogicException) {
                return new ResponseEntity<>(
                        new ResponseBody(e.getMessage(), ((RuntimeBusinessLogicException) e).getCode(), null),
                        HttpStatus.BAD_REQUEST
                );
            }
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(RuntimeBusinessLogicException.class)
    public ResponseEntity<?> handleRuntimeBusinessLogicError(RuntimeBusinessLogicException e) {
        return new ResponseEntity<>(
                new ResponseBody(e.getMessage(), ((RuntimeBusinessLogicException) e).getCode(), null),
                HttpStatus.BAD_REQUEST
        );
    }
}
