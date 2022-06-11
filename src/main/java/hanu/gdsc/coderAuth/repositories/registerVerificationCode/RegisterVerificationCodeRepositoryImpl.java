package hanu.gdsc.coderAuth.repositories.registerVerificationCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coderAuth.domains.RegisterVerificationCode;
import hanu.gdsc.share.domains.Id;

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
