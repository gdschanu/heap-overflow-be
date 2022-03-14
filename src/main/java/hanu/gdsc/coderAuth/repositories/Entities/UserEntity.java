package hanu.gdsc.coderAuth.repositories.Entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String email;
    private String username;
    private String password;
    private UUID coderId;
    private boolean registrationConfirmed;

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
        .id(user.getId().toUUID())
        .email(user.getEmail().toString())
        .username(user.getUsername().toString())
        .password(user.getPassword().toString())
        .coderId(user.getCoderId().toUUID())
        .registrationConfirmed(user.isRegistrationConfirmed())
        .build();
    }

    public User toDomain() {
        return new User(
            new hanu.gdsc.share.domains.Id(id),
            new Email(email),
            new Username(username),
            new Password(password),
            new hanu.gdsc.share.domains.Id(coderId),
            registrationConfirmed
        );
    }
    
}