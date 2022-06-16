package hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.session;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.Session;
import hanu.gdsc.share.domains.Id;

public interface SessionRepository {
    public void save(Session session);

    public Session getById(Id id);

    public Session getByCoderId(Id coderId);

    public void deleteById(Id id);

    public void deleteSession(Id coderId, Id sessionId);
}
