package hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.user;

import java.lang.reflect.Constructor;

import javax.persistence.*;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.Email;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.HashedPassword;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.User;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.Username;
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
               Email.class,
               Username.class,
               HashedPassword.class,
               hanu.gdsc.share.domains.Id.class,
               Boolean.TYPE
           );
           con.setAccessible(true);
           return con.newInstance(
               version,
               new Email(email),
               new Username(username),
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