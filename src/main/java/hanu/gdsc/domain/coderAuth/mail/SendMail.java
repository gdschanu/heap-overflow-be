package hanu.gdsc.domain.coderAuth.mail;

import hanu.gdsc.domain.coderAuth.models.Email;
import hanu.gdsc.domain.coderAuth.exceptions.EmailSendingException;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;


public interface SendMail {
    public void sendMail(Email toAddress, String name, String code) throws InvalidInputException, EmailSendingException;
}
