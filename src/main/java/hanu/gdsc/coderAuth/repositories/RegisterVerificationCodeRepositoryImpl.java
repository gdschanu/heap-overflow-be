package hanu.gdsc.coderAuth.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coderAuth.domains.RegisterVerificationCode;
import hanu.gdsc.coderAuth.repositories.Entities.RegisterVerificationCodeEntity;
import hanu.gdsc.coderAuth.repositories.JPA.RegisterVerificationCodeJPARepository;

@Repository
public class RegisterVerificationCodeRepositoryImpl implements RegisterVerificationCodeRepository {
    @Autowired
    private RegisterVerificationCodeJPARepository registerVerificationCodeJPARepository;

    public void save(RegisterVerificationCode registerVerificationCode) {
        registerVerificationCodeJPARepository
        .save(RegisterVerificationCodeEntity.toEntity(registerVerificationCode));
    }

    public RegisterVerificationCode getByCode(String code) {
        RegisterVerificationCodeEntity registerVerificationCodeEntity = 
        registerVerificationCodeJPARepository.getByCode(code);
        return registerVerificationCodeEntity.toDomain();      
    }
}
