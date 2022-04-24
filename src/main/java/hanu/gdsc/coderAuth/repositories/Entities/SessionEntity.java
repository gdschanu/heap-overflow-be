package hanu.gdsc.coderAuth.repositories.Entities;

import javax.persistence.*;

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
        return new Session(
            new hanu.gdsc.share.domains.Id(id), 
            new hanu.gdsc.share.domains.Id(coderId),
            new hanu.gdsc.share.domains.DateTime(expireAt)
        );
    }
}
