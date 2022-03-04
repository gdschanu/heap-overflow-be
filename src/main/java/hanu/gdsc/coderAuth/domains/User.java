package hanu.gdsc.coderAuth.domains;

import javax.persistence.Entity;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}