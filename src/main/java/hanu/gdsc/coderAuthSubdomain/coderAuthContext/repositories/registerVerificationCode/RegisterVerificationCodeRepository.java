package hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.registerVerificationCode;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.RegisterVerificationCode;
import hanu.gdsc.share.domains.Id;

public interface RegisterVerificationCodeRepository {

    void save(RegisterVerificationCode registerVerificationCode);

    RegisterVerificationCode getByCoderId(Id id);
    
}
