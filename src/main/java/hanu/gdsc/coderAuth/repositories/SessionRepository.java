package hanu.gdsc.coderAuth.repositories;

import hanu.gdsc.coderAuth.domains.Session;
import hanu.gdsc.share.domains.Id;

public interface SessionRepository {
    public void save(Session session);

    public Session getById(Id id);

    public Session getByCoderId(Id coderId);
}
