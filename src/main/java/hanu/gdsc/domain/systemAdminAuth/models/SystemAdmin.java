package hanu.gdsc.domain.systemAdminAuth.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentitifedVersioningDomainObject;

public class SystemAdmin extends IdentitifedVersioningDomainObject{
    private Email email;
    private Username username;
    private HashedPassword password;

    public SystemAdmin(long version, Email email, Username username, HashedPassword password, Id systemAdminId) {
        super(systemAdminId, version);
        this.email = email;
        this.username = username;
        this.password = password;}


    public Email getEmail() {
        return email;
    }

    public Username getUsername() {
        return username;
    }

    public HashedPassword getPassword() {
        return password;
    }

    public void setPassword(HashedPassword password) {
        this.password = password;
    }
}