package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.RegisterVerificationCode;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.exceptions.ConfirmedUserException;
import hanu.gdsc.coderAuth.exceptions.ExpiredCodeException;
import hanu.gdsc.coderAuth.exceptions.WrongCodeException;
import hanu.gdsc.coderAuth.repositories.registerVerificationCode.RegisterVerificationCodeRepository;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.exceptions.UnsentCodeException;
import hanu.gdsc.share.domains.Id;

@Service
public class ConfirmRegisterVerificationCodeService {

    @Autowired
    private RegisterVerificationCodeRepository registerVerificationCodeRepository;

    @Autowired
    private UserRepository userRepository;

    public void confirmRegisterVerificationCode(String code, Id coderId) throws UnsentCodeException, ExpiredCodeException, WrongCodeException, ConfirmedUserException {
       RegisterVerificationCode registerVerificationCode = registerVerificationCodeRepository.getByCoderId(coderId);
       if(registerVerificationCode == null) {
           throw new UnsentCodeException();
       }
       if(registerVerificationCode.invalidate()) {
           throw new ExpiredCodeException("Your register verification code is expired");
       }
       if(!registerVerificationCode.getCode().equals(code)) {
           throw new WrongCodeException();
       }
       User user = userRepository.getByCoderId(coderId);
       user.confirmRegistration();
       userRepository.save(user);
    }  
}
