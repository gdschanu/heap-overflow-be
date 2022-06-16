package hanu.gdsc.coderAuth_coderAuth.repositories.registerVerificationCode;

import hanu.gdsc.coderAuth_coderAuth.domains.RegisterVerificationCode;
import hanu.gdsc.share.domains.Id;

public interface RegisterVerificationCodeRepository {

    void save(RegisterVerificationCode registerVerificationCode);

    RegisterVerificationCode getByCoderId(Id id);
    
}
