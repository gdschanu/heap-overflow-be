package hanu.gdsc.coderAuth.repositories.Entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import hanu.gdsc.coderAuth.domains.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coder_auth_session")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionEntity {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private UUID coderId;
    private String expiredAt;    

    public static SessionEntity toEntity(Session session) {
        return SessionEntity.builder()
        .id(session.getId().toUUID())
        .coderId(session.getCoderId().toUUID())
        .expiredAt(session.getExpiredAt().toZonedDateTime().toString())
        .build();
    }
}
