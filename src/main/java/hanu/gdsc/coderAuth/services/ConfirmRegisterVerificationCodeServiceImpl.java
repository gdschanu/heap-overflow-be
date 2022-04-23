package hanu.gdsc.coderAuth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.RegisterVerificationCode;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.repositories.RegisterVerificationCodeRepository;
import hanu.gdsc.coderAuth.repositories.UserRepository;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class ConfirmRegisterVerificationCodeServiceImpl implements ConfirmRegisterVerificationCodeService {

    @Autowired
    private RegisterVerificationCodeRepository registerVerificationCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void confirmRegisterVerificationCode(String code, Id coderId) {
       RegisterVerificationCode registerVerificationCode = registerVerificationCodeRepository.getByCode(code);
       if(registerVerificationCode == null) {
           throw new BusinessLogicError("Your code is wrong", "WRONG_CODE");
       }

       DateTime time = new DateTime(DateTime.now().toString());
       if(!time.isBefore(registerVerificationCode.getExpireAt())) {
           throw new BusinessLogicError("Your code is expired", "EXPIRED_CODE");
       }

       User user = userRepository.getByCoderId(coderId);
       user.setRegistrationConfirmed(true);
    }  
}
