package hanu.gdsc.domain.coderAuth.repositories;

import hanu.gdsc.domain.coderAuth.models.RegisterVerificationCode;
import hanu.gdsc.domain.share.models.Id;

public interface RegisterVerificationCodeRepository {

    void save(RegisterVerificationCode registerVerificationCode);

    RegisterVerificationCode getByCoderId(Id id);
    
}
