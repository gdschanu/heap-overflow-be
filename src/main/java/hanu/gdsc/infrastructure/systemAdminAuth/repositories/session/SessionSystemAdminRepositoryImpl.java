package hanu.gdsc.infrastructure.systemAdminAuth.repositories.session;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.systemAdminAuth.models.SessionSystemAdmin;
import hanu.gdsc.domain.systemAdminAuth.repositories.SessionSystemAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class SessionSystemAdminRepositoryImpl implements SessionSystemAdminRepository {

    @Autowired
    private SessionSystemAdminJPARepository sessionJPARepository;

    @Override
    public void save(SessionSystemAdmin session) {
      sessionJPARepository.save(SessionSystemAdminEntity.toEntity(session));
    }

    @Override
    public SessionSystemAdmin getById(Id id) {
      return sessionJPARepository.getById(id.toString()).toDomain();
    }

    @Override
    public SessionSystemAdmin getBySystemAdminId(Id systemAdminId) {
      return sessionJPARepository.getBySystemAdminId(systemAdminId.toString()).toDomain();
    }

    @Override
    public void deleteById(Id id) {
      sessionJPARepository.deleteById(id.toString());
    }

    @Override
    public void deleteSession(Id systemAdminId, Id sessionId) {
      sessionJPARepository.deleteSession(systemAdminId.toString(), sessionId.toString());
    }
}
