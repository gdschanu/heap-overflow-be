package hanu.gdsc.domain.coderAuth.services;

import hanu.gdsc.domain.coderAuth.models.Email;
import hanu.gdsc.domain.coderAuth.models.User;
import hanu.gdsc.domain.coderAuth.exceptions.EmailSendingException;
import hanu.gdsc.domain.coderAuth.mail.SendMail;
import hanu.gdsc.domain.coderAuth.repositories.RegisterVerificationCodeRepository;
import hanu.gdsc.domain.coderAuth.repositories.UserRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.domain.coderAuth.models.RegisterVerificationCode;
import hanu.gdsc.domain.coderAuth.exceptions.ExpiredTokenException;
import hanu.gdsc.domain.share.models.Id;

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
