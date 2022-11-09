package hanu.gdsc.infrastructure.systemAdminAuth.repositories.systemAdmin;

import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.systemAdminAuth.models.Email;
import hanu.gdsc.domain.systemAdminAuth.models.SystemAdmin;
import hanu.gdsc.domain.systemAdminAuth.models.Username;
import hanu.gdsc.domain.systemAdminAuth.repositories.SystemAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SystemAdminRepositoryImpl implements SystemAdminRepository {

    @Autowired
    private SystemAdminJPARepository systemAdminJPARepository;

    @Override
    public SystemAdmin getByUsername(Username username) throws InvalidInputException {
        SystemAdminEntity systemAdminEntity = systemAdminJPARepository.getByUsername(username.toString());
        SystemAdmin systemAdmin = null;
        if(systemAdminEntity != null) {
            systemAdmin = systemAdminEntity.toDomain();
        } 
        return systemAdmin;
    }

    @Override
    public SystemAdmin getByEmail(Email email) throws InvalidInputException {
        SystemAdminEntity systemAdminEntity = systemAdminJPARepository.getByEmail(email.toString());
        SystemAdmin systemAdmin = null;
        if(systemAdminEntity != null) {
            systemAdmin = systemAdminEntity.toDomain();
        }
        return systemAdmin;
    }

    @Override
    public void save(SystemAdmin systemAdmin) {
        systemAdminJPARepository.save(SystemAdminEntity.toEntity(systemAdmin));
    }

    @Override
    public SystemAdmin getBySystemAdminId(Id systemAdminId) throws InvalidInputException {
        return systemAdminJPARepository.getBySystemAdminId(systemAdminId.toString()).toDomain();
    }

    @Override
    public List<SystemAdmin> getBySystemAdminIds(List<Id> systemAdminIds) {
        List<SystemAdmin> systemAdmins = systemAdminJPARepository.getBySystemAdminIds
                (systemAdminIds.stream().map(x -> x.toString()).collect(Collectors.toList()))
                .stream().map(x -> {
                    try {
                        return x.toDomain();
                    } catch (InvalidInputException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
        return systemAdmins;
    }

}
