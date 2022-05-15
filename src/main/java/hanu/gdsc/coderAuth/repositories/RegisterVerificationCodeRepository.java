package hanu.gdsc.coderAuth.repositories;

import hanu.gdsc.coderAuth.domains.RegisterVerificationCode;
import hanu.gdsc.share.domains.Id;

public interface RegisterVerificationCodeRepository {

    void save(RegisterVerificationCode registerVerificationCode);

    RegisterVerificationCode getByCoderId(Id id);
    
}
