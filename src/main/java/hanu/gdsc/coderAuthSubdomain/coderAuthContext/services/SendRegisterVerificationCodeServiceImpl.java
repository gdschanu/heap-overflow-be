package hanu.gdsc.coderAuthSubdomain.coderAuthContext.services;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.Email;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.User;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.registerVerificationCode.RegisterVerificationCodeRepository;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.RegisterVerificationCode;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors.ExpiredToken;
import hanu.gdsc.share.domains.Id;

@Service
public class SendRegisterVerificationCodeServiceImpl implements SendRegisterVerificationCodeService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterVerificationCodeRepository registerVerificationCodeRepository;

    @Autowired
    private SendMailService sendMailService;

    @Override
    public void sendRegisterVerificationCodeService(Id coderId) {
        User user = userRepository.getByCoderId(coderId);
        if(user == null) {
            throw new ExpiredToken();
        }
        String name = user.getUsername().toString();
        Email toAddress = user.getEmail();
        RegisterVerificationCode registerVerificationCode = RegisterVerificationCode.createRegisterVerificationCode(coderId);
        registerVerificationCodeRepository.save(registerVerificationCode);

        sendMailService.sendMail(toAddress, name, registerVerificationCode.getCode());      
    }
}
