package hanu.gdsc.coderAuth.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coderAuth.repositories.Entities.RegisterVerificationCodeEntity;

public interface RegisterVerificationCodeJPARepository extends JpaRepository<RegisterVerificationCodeEntity, String> {
    RegisterVerificationCodeEntity getByCoderId(String coderId);
}
