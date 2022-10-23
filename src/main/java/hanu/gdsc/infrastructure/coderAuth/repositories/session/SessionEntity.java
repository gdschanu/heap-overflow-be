package hanu.gdsc.infrastructure.coderAuth.repositories.session;

import java.lang.reflect.Constructor;

import javax.persistence.*;

import hanu.gdsc.domain.coderAuth.models.Session;
import hanu.gdsc.domain.share.models.DateTime;
import lombok.*;

@Entity
@Table(name = "coder_auth_session")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;
    private String expireAt;    

    public static SessionEntity toEntity(Session session) {
        return SessionEntity.builder()
        .id(session.getId().toString())
        .coderId(session.getCoderId().toString())
        .expireAt(session.getExpireAt().toZonedDateTime().toString())
        .build();
    }

    public Session toDomain() {
        try {
            Constructor<Session> con = Session.class.getDeclaredConstructor(
                hanu.gdsc.domain.share.models.Id.class,
                hanu.gdsc.domain.share.models.Id.class,
                DateTime.class
            );
            con.setAccessible(true);
            return con.newInstance(
                new hanu.gdsc.domain.share.models.Id(id),
                new hanu.gdsc.domain.share.models.Id(coderId),
                new DateTime(expireAt)
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
