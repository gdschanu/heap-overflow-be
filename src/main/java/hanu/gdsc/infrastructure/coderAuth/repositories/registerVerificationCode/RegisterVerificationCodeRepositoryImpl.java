package hanu.gdsc.infrastructure.coderAuth.repositories.registerVerificationCode;

import hanu.gdsc.domain.coderAuth.models.RegisterVerificationCode;
import hanu.gdsc.domain.coderAuth.repositories.RegisterVerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.domain.share.models.Id;

@Repository
public class RegisterVerificationCodeRepositoryImpl implements RegisterVerificationCodeRepository {
    @Autowired
    private RegisterVerificationCodeJPARepository registerVerificationCodeJPARepository;

    public void save(RegisterVerificationCode registerVerificationCode) {
        registerVerificationCodeJPARepository
        .save(RegisterVerificationCodeEntity.toEntity(registerVerificationCode));
    }

    @Override
    public RegisterVerificationCode getByCoderId(Id coderId) {
       return registerVerificationCodeJPARepository.getByCoderId(coderId.toString()).toDomain();
    }
}
