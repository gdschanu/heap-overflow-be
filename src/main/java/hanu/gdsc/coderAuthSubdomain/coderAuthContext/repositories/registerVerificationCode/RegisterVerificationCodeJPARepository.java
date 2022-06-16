package hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.registerVerificationCode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterVerificationCodeJPARepository extends JpaRepository<RegisterVerificationCodeEntity, String> {
    RegisterVerificationCodeEntity getByCoderId(String coderId);
}
