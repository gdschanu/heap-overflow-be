package hanu.gdsc.coderAuth.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import hanu.gdsc.coderAuth.repositories.Entities.ForgotPasswordCodeEntity;

public interface ForgotPasswordCodeJPARepository extends JpaRepository<ForgotPasswordCodeEntity, String> {
    ForgotPasswordCodeEntity getByCoderId(String coderId);
}
