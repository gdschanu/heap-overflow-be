package hanu.gdsc.infrastructure.coderAuth.repositories.forgotPasswordCode;

import hanu.gdsc.domain.coderAuth.repositories.ForgotPasswordCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.domain.coderAuth.models.ForgotPasswordCode;
import hanu.gdsc.domain.share.models.Id;

@Repository
public class ForgotPasswordCodeRepositoryImpl implements ForgotPasswordCodeRepository {

    @Autowired
    private ForgotPasswordCodeJPARepository forgotPasswordCodeJPARepository;

    @Override
    public void save(ForgotPasswordCode forgotPasswordCode) {
        forgotPasswordCodeJPARepository.save(ForgotPasswordCodeEntity.toEntity(forgotPasswordCode));
    }

    @Override
    public ForgotPasswordCode getByCoderId(Id coderId) {
       return forgotPasswordCodeJPARepository.getByCoderId(coderId.toString()).toDomain();
    }  
}
