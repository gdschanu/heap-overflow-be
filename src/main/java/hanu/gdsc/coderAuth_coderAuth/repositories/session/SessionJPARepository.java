package hanu.gdsc.coderAuth_coderAuth.repositories.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SessionJPARepository extends JpaRepository<SessionEntity, String>{
    SessionEntity getById(String id);
    SessionEntity getByCoderId(String coderId);  
    void deleteById(String id);
    @Query(value = "DELETE FROM coder_auth_session s WHERE s.coder_id = :coderId AND s.id != :id", nativeQuery = true)
    @Modifying
    void deleteSession(String coderId, String id);
}
