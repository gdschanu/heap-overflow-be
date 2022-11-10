package hanu.gdsc.infrastructure.systemAdminAuth.repositories.systemAdmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemAdminJPARepository extends JpaRepository<SystemAdminEntity,String> {
    SystemAdminEntity getByEmail(String email);
    SystemAdminEntity getByUsername(String username);
    SystemAdminEntity getBySystemAdminId(String systemAdminId);
    @Query(value = "SELECT * FROM system_admin_auth_system_admin u WHERE u.system_admin_id in :systemAdminIds", nativeQuery = true)
    List<SystemAdminEntity> getBySystemAdminIds(List<String> systemAdminIds);
}
