package hanu.gdsc.coderAuth.repositories.Entities;

import javax.persistence.Entity;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    private Id id;
    private String email;
    private String username;
    private String password;
    private Id coderId;
    private boolean registrationConfirmed;
    
}