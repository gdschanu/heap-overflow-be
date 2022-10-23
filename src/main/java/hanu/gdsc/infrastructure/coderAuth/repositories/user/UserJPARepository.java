package hanu.gdsc.infrastructure.coderAuth.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJPARepository extends JpaRepository<UserEntity,String> {
    UserEntity getByEmail(String email);
    UserEntity getByUsername(String username);   
    UserEntity getByCoderId(String coderId);
    @Query(value = "SELECT * FROM coder_auth_user u WHERE u.coder_id in :coderIds", nativeQuery = true)
    List<UserEntity> getByCoderIds(List<String> coderIds);
}
