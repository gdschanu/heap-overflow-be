package hanu.gdsc.coderAuth.repositories.JPA;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coderAuth.repositories.Entities.UserEntity;

public interface UserJPARepository extends JpaRepository<UserEntity,UUID> {

    UserEntity getByEmail(String email);

    UserEntity getByUsername(String username);
    
}
