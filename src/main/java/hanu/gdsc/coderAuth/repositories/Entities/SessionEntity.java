package hanu.gdsc.coderAuth.repositories.Entities;

import java.util.UUID;

import javax.persistence.*;

import hanu.gdsc.coderAuth.domains.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
    private String expiredAt;    

    public static SessionEntity toEntity(Session session) {
        return SessionEntity.builder()
        .id(session.getId().toString())
        .coderId(session.getCoderId().toString())
        .expiredAt(session.getExpiredAt().toZonedDateTime().toString())
        .build();
    }
}
