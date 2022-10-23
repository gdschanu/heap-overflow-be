package hanu.gdsc.domain.coderAuth.repositories;

import hanu.gdsc.domain.coderAuth.models.Session;
import hanu.gdsc.domain.share.models.Id;

public interface SessionRepository {
    public void save(Session session);

    public Session getById(Id id);

    public Session getByCoderId(Id coderId);

    public void deleteById(Id id);

    public void deleteSession(Id coderId, Id sessionId);
}
