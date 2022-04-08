package hanu.gdsc.coderAuth.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coderAuth.repositories.Entities.SessionEntity;

public interface SessionJPARepository extends JpaRepository<SessionEntity, String>{
    SessionEntity getById(String id);  
}
