package hanu.gdsc.coderAuth.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coderAuth.repositories.Entities.UserEntity;

public interface UserJPARepository extends JpaRepository<UserEntity,String> {
    UserEntity getByEmail(String email);
    UserEntity getByUsername(String username);   
    UserEntity getById(String id);
    UserEntity getByCoderId(String coderId);
}
