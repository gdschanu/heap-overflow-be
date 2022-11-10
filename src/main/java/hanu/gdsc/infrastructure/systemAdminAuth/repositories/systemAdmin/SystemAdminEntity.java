package hanu.gdsc.infrastructure.systemAdminAuth.repositories.systemAdmin;

import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.systemAdminAuth.models.Email;
import hanu.gdsc.domain.systemAdminAuth.models.HashedPassword;
import hanu.gdsc.domain.systemAdminAuth.models.SystemAdmin;
import hanu.gdsc.domain.systemAdminAuth.models.Username;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "system_admin_auth_system_admin")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SystemAdminEntity {
    @Version
    private long version;
    private String email;
    private String username;
    private String password;
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String systemAdminId;

    public static SystemAdminEntity toEntity(SystemAdmin systemAdmin) {
        return SystemAdminEntity.builder()
        .version(systemAdmin.getVersion())
        .email(systemAdmin.getEmail().toString())
        .username(systemAdmin.getUsername().toString())
        .password(systemAdmin.getPassword().toHashedPasswordString())
        .systemAdminId(systemAdmin.getId().toString())
        .build();
    }

    public SystemAdmin toDomain() throws InvalidInputException {
        return new SystemAdmin(version, new Email(email), new Username(username),
                HashedPassword.fromHashedPassword(password), new hanu.gdsc.domain.share.models.Id(systemAdminId));
    }
    
}