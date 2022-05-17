package hanu.gdsc.coderAuth.repositories.Entities;

import java.lang.reflect.Constructor;

import javax.persistence.*;

import hanu.gdsc.coderAuth.domains.HashedPassword;
import hanu.gdsc.coderAuth.domains.User;
import lombok.*;

@Entity
@Table(name = "coder_auth_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Version
    private long version;
    private String email;
    private String username;
    private String password;
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;
    private boolean registrationConfirmed;

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
        .version(user.getVersion())
        .email(user.getEmail().toString())
        .username(user.getUsername().toString())
        .password(user.getPassword().toHashedPasswordString())
        .coderId(user.getId().toString())
        .registrationConfirmed(user.isRegistrationConfirmed())
        .build();
    }

    public User toDomain() {
       try {
           Constructor<User> con = User.class.getDeclaredConstructor(
               Long.TYPE,
               hanu.gdsc.coderAuth.domains.Email.class,
               hanu.gdsc.coderAuth.domains.Username.class,
               hanu.gdsc.coderAuth.domains.HashedPassword.class,
               hanu.gdsc.share.domains.Id.class,
               Boolean.TYPE
           );
           con.setAccessible(true);
           return con.newInstance(
               version,
               new hanu.gdsc.coderAuth.domains.Email(email),
               new hanu.gdsc.coderAuth.domains.Username(username),
               HashedPassword.fromHashedPassword(password),
               new hanu.gdsc.share.domains.Id(coderId),
               registrationConfirmed
           );
       } catch (Exception e) {
          e.printStackTrace();
          throw new Error(e);
       }
    }
    
}