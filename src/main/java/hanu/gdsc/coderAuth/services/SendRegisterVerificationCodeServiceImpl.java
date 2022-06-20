package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.repositories.registerVerificationCode.RegisterVerificationCodeRepository;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.RegisterVerificationCode;
import hanu.gdsc.coderAuth.errors.ExpiredToken;
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
