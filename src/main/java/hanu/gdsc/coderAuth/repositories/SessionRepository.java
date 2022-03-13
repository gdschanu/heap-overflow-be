package hanu.gdsc.coderAuth.repositories;

import hanu.gdsc.coderAuth.domains.Session;

public interface SessionRepository {
    public void save(Session session);
}
