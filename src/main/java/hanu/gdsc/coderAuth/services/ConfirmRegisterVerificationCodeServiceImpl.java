package hanu.gdsc.coderAuth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.RegisterVerificationCode;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.errors.ExpiredCode;
import hanu.gdsc.coderAuth.errors.UnsentCode;
import hanu.gdsc.coderAuth.errors.WrongCode;
import hanu.gdsc.coderAuth.repositories.RegisterVerificationCodeRepository;
import hanu.gdsc.coderAuth.repositories.UserRepository;
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
