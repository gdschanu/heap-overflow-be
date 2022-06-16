package hanu.gdsc.coderAuth_coderAuth.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class EmailSendingError extends BusinessLogicError {
    public EmailSendingError() {
        super("Error in sending email", "EMAIL_SENDING_ERROR");
    }
}
