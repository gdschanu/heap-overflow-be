package hanu.gdsc.domain.coderAuth.services.register;

import hanu.gdsc.domain.coderAuth.models.RegisterVerificationCode;
import hanu.gdsc.domain.coderAuth.models.User;
import hanu.gdsc.domain.coderAuth.exceptions.ConfirmedUserException;
import hanu.gdsc.domain.coderAuth.exceptions.ExpiredCodeException;
import hanu.gdsc.domain.coderAuth.exceptions.WrongCodeException;
import hanu.gdsc.domain.coderAuth.repositories.RegisterVerificationCodeRepository;
import hanu.gdsc.domain.coderAuth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.domain.coderAuth.exceptions.UnsentCodeException;
import hanu.gdsc.domain.share.models.Id;

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
