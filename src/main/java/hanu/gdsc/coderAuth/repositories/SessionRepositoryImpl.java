package hanu.gdsc.coderAuth.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coderAuth.domains.Session;
import hanu.gdsc.coderAuth.repositories.Entities.SessionEntity;
import hanu.gdsc.coderAuth.repositories.JPA.SessionJPARepository;

@Repository
public class SessionRepositoryImpl implements SessionRepository{

    @Autowired
    private SessionJPARepository sessionJPARepository;

    @Override
    public void save(Session session) {
      sessionJPARepository.save(SessionEntity.toEntity(session));
    }
}
