package hanu.gdsc.infrastructure.systemAdminAuth.repositories.session;

import hanu.gdsc.domain.systemAdminAuth.models.SessionSystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SessionSystemAdminJPARepository extends JpaRepository<SessionSystemAdminEntity, String>{
    SessionSystemAdminEntity getById(String id);
    SessionSystemAdminEntity getBySystemAdminId(String systemAdminId);
    void deleteById(String id);
    @Query(value = "DELETE FROM system_admin_auth_session s WHERE s.system_admin_id = :systemAdminId AND s.id != :id", nativeQuery = true)
    @Modifying
    void deleteSession(String systemAdminId, String id);
}
