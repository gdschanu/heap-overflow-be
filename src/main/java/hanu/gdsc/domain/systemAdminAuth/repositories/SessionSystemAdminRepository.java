package hanu.gdsc.domain.systemAdminAuth.repositories;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.systemAdminAuth.models.SessionSystemAdmin;

public interface SessionSystemAdminRepository {
    public void save(SessionSystemAdmin session);

    public SessionSystemAdmin getById(Id id);

    public SessionSystemAdmin getBySystemAdminId(Id systemAdminId);

    public void deleteById(Id id);

    public void deleteSession(Id systemAdminId, Id sessionId);
}
