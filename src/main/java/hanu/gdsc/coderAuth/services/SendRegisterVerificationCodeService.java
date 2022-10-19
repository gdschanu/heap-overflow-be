package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.exceptions.EmailSendingException;
import hanu.gdsc.coderAuth.mail.SendMail;
import hanu.gdsc.coderAuth.repositories.registerVerificationCode.RegisterVerificationCodeRepository;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.share.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.RegisterVerificationCode;
import hanu.gdsc.coderAuth.exceptions.ExpiredTokenException;
import hanu.gdsc.share.domains.Id;

@Service
public class SendRegisterVerificationCodeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterVerificationCodeRepository registerVerificationCodeRepository;

    @Autowired
    private SendMail sendMail;

    public void sendRegisterVerificationCodeService(Id coderId) throws ExpiredTokenException, InvalidInputException, EmailSendingException {
        User user = userRepository.getByCoderId(coderId);
        if(user == null) {
            throw new ExpiredTokenException();
        }
        String name = user.getUsername().toString();
        Email toAddress = user.getEmail();
        RegisterVerificationCode registerVerificationCode = RegisterVerificationCode.createRegisterVerificationCode(coderId);
        registerVerificationCodeRepository.save(registerVerificationCode);

        sendMail.sendMail(toAddress, name, registerVerificationCode.getCode());
    }
}
