package hanu.gdsc.infrastructure.systemAdminAuth.repositories.session;

import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.systemAdminAuth.models.SessionSystemAdmin;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "system_admin_auth_session")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionSystemAdminEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Column(columnDefinition = "VARCHAR(30)")
    private String systemAdminId;
    private String expireAt;    

    public static SessionSystemAdminEntity toEntity(SessionSystemAdmin session) {
        return SessionSystemAdminEntity.builder()
        .id(session.getId().toString())
        .systemAdminId(session.getSystemAdminId().toString())
        .expireAt(session.getExpireAt().toZonedDateTime().toString())
        .build();
    }

    public SessionSystemAdmin toDomain() {
        try {
            Constructor<SessionSystemAdmin> con = SessionSystemAdmin.class.getDeclaredConstructor(
                hanu.gdsc.domain.share.models.Id.class,
                hanu.gdsc.domain.share.models.Id.class,
                DateTime.class
            );
            con.setAccessible(true);
            return con.newInstance(
                new hanu.gdsc.domain.share.models.Id(id),
                new hanu.gdsc.domain.share.models.Id(systemAdminId),
                new DateTime(expireAt)
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
