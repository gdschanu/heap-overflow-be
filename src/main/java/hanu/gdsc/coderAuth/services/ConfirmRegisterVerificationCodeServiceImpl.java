package hanu.gdsc.coderAuth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coderAuth.domains.RegisterVerificationCode;
import hanu.gdsc.coderAuth.repositories.RegisterVerificationCodeRepository;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.error.BusinessLogicError;

@Service
public class ConfirmRegisterVerificationCodeServiceImpl implements ConfirmRegisterVerificationCodeService {

    @Autowired
    private RegisterVerificationCodeRepository registerVerificationCodeRepository;

    @Override
    public void confirmRegisterVerificationCode(String code) {
       RegisterVerificationCode registerVerificationCode = registerVerificationCodeRepository.getByCode(code);
       if(registerVerificationCode == null) {
           throw new BusinessLogicError("Nhập sai mã", "WRONG_CODE");
       }

       DateTime time = new DateTime(DateTime.now().toString());
       if(time.isBefore(registerVerificationCode.getExpiredAt())) {
           throw new BusinessLogicError("Mã đã hết hiệu lực", "EXPIRED_CODE");
       }

    }  
}
