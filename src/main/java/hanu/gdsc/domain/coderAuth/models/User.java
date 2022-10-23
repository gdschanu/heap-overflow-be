package hanu.gdsc.domain.coderAuth.models;

import hanu.gdsc.domain.coderAuth.exceptions.ConfirmedUserException;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.models.IdentitifedVersioningDomainObject;

public class User extends IdentitifedVersioningDomainObject{
    private Email email;
    private Username username;
    private HashedPassword password;
    private boolean registrationConfirmed;

    private User(long version, Email email, Username username, HashedPassword password, Id coderId, boolean registrationConfirmed) {
        super(coderId, version);
        this.email = email;
        this.username = username;
        this.password = password;
        this.registrationConfirmed = registrationConfirmed;
    }

    public static User createUser(Email email, Username username, HashedPassword password, Id coderId) {
        return new User(0, 
        email, 
        username, 
        password, 
        coderId, 
        false);
    }

    public Email getEmail() {
        return email;
    }

    public Username getUsername() {
        return username;
    }

    public HashedPassword getPassword() {
        return password;
    }

    public boolean isRegistrationConfirmed() {
        return registrationConfirmed;
    }

    public void setPassword(HashedPassword password) {
        this.password = password;
    }

    public void confirmRegistration() throws ConfirmedUserException {
        if (registrationConfirmed == true) {
            throw new ConfirmedUserException();
        } else {
            this.registrationConfirmed = true;
        }
    }
}