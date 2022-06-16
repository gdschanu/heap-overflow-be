package hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.forgotPasswordCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.ForgotPasswordCode;
import hanu.gdsc.share.domains.Id;

@Repository
public class ForgotPasswordCodeRepositoryImpl implements ForgotPasswordCodeRepository{

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
