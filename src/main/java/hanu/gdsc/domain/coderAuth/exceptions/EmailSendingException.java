package hanu.gdsc.domain.coderAuth.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class EmailSendingException extends BusinessLogicException {
    public EmailSendingException() {
        super("Error in sending email", "EMAIL_SENDING_ERROR");
    }
}
