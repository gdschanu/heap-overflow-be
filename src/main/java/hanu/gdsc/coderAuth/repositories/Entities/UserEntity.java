package hanu.gdsc.coderAuth.repositories.Entities;

import javax.persistence.*;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.Password;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.domains.Username;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coder_auth_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    private String email;
    private String username;
    private String password;
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;
    private boolean registrationConfirmed;

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
        .email(user.getEmail().toString())
        .username(user.getUsername().toString())
        .password(user.getPassword().toString())
        .coderId(user.getCoderId().toString())
        .registrationConfirmed(user.isRegistrationConfirmed())
        .build();
    }

    public User toDomain() {
        return new User(
            new Email(email),
            new Username(username),
            new Password(password),
            new hanu.gdsc.share.domains.Id(coderId),
            registrationConfirmed
        );
    }
    
}