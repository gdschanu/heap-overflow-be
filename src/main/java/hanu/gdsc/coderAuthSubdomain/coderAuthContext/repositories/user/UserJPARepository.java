package hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<UserEntity,String> {
    UserEntity getByEmail(String email);
    UserEntity getByUsername(String username);   
    UserEntity getByCoderId(String coderId);
}
