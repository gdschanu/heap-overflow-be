package hanu.gdsc.coderAuthSubdomain.coderAuthContext.services;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.RegisterVerificationCode;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.User;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors.ExpiredCode;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors.WrongCode;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.registerVerificationCode.RegisterVerificationCodeRepository;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.errors.UnsentCode;
import hanu.gdsc.share.domains.Id;

@Service
public class ConfirmRegisterVerificationCodeServiceImpl implements ConfirmRegisterVerificationCodeService {

    @Autowired
    private RegisterVerificationCodeRepository registerVerificationCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void confirmRegisterVerificationCode(String code, Id coderId) {
       RegisterVerificationCode registerVerificationCode = registerVerificationCodeRepository.getByCoderId(coderId);
       if(registerVerificationCode == null) {
           throw new UnsentCode();
       }
       if(registerVerificationCode.invalidate()) {
           throw new ExpiredCode("Your register verification code is expired");
       }
       if(!registerVerificationCode.getCode().equals(code)) {
           throw new WrongCode();
       }
       User user = userRepository.getByCoderId(coderId);
       user.confirmRegistration();
       userRepository.save(user);
    }  
}
