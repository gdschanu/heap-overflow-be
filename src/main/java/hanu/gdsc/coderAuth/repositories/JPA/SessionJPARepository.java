package hanu.gdsc.coderAuth.repositories.JPA;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coderAuth.repositories.Entities.SessionEntity;

public interface SessionJPARepository extends JpaRepository<SessionEntity, UUID>{
    
}
