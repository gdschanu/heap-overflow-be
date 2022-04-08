package hanu.gdsc.coderAuth.repositories;

import hanu.gdsc.coderAuth.domains.RegisterVerificationCode;

public interface RegisterVerificationCodeRepository {

    void save(RegisterVerificationCode registerVerificationCode);

    RegisterVerificationCode getByCode(String code);
    
}
