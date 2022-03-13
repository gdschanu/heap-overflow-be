package hanu.gdsc.coderAuth.domains;

import hanu.gdsc.share.domains.Id;

public class User {
    private Id id;
    private Email email;
    private Username username;
    private Password password;
    private Id coderId;
    private boolean registrationConfirmed;

    public User(Id id, Email email, Username username, Password password, Id coderId, boolean registrationConfirmed) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.coderId = coderId;
        this.registrationConfirmed = registrationConfirmed;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Id getCoderId() {
        return coderId;
    }

    public void setCoderId(Id coderId) {
        this.coderId = coderId;
    }

    public boolean isRegistrationConfirmed() {
        return registrationConfirmed;
    }

    public void setRegistrationConfirmed(boolean registrationConfirmed) {
        this.registrationConfirmed = registrationConfirmed;
    }
    
}