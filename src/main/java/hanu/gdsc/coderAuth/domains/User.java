package hanu.gdsc.coderAuth.domains;

import hanu.gdsc.share.domains.Id;

public class User {
    private Id id;
    private String email;
    private String username;
    private String password;
    private Id coderId;
    private boolean registrationConfirmed;

    public User(String username, String email, String password, Id coderId) {
        this.username=username;
        this.email=email;
        this.password=password;
        this.coderId=coderId;
    }
    

    /**
     * @return Id return the id
     */
    public Id getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Id id) {
        this.id = id;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Id return the coderId
     */
    public Id getCoderId() {
        return coderId;
    }

    /**
     * @param coderId the coderId to set
     */
    public void setCoderId(Id coderId) {
        this.coderId = coderId;
    }

    /**
     * @return boolean return the registrationConfirmed
     */
    public boolean isRegistrationConfirmed() {
        return registrationConfirmed;
    }

    /**
     * @param registrationConfirmed the registrationConfirmed to set
     */
    public void setRegistrationConfirmed(boolean registrationConfirmed) {
        this.registrationConfirmed = registrationConfirmed;
    }

}