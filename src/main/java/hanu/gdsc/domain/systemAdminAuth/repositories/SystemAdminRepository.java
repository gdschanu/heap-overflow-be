package hanu.gdsc.domain.systemAdminAuth.repositories;

import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.systemAdminAuth.models.Email;
import hanu.gdsc.domain.systemAdminAuth.models.SystemAdmin;
import hanu.gdsc.domain.systemAdminAuth.models.Username;

import java.util.List;

public interface SystemAdminRepository {
    public SystemAdmin getByUsername(Username username) throws InvalidInputException;
    public SystemAdmin getByEmail(Email email) throws InvalidInputException;
    public void save(SystemAdmin systemAdmin);
    public SystemAdmin getBySystemAdminId(Id systemAdminId) throws InvalidInputException;

    public List<SystemAdmin> getBySystemAdminIds(List<Id> systemAdminIds);
}
