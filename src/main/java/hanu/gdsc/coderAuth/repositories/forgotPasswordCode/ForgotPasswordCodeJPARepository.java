package hanu.gdsc.coderAuth.repositories.forgotPasswordCode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgotPasswordCodeJPARepository extends JpaRepository<ForgotPasswordCodeEntity, String> {
    ForgotPasswordCodeEntity getByCoderId(String coderId);
}
