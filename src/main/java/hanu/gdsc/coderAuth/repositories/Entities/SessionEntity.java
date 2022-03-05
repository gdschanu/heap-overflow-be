package hanu.gdsc.coderAuth.repositories.Entities;

import javax.persistence.Entity;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionEntity {
    private Id id;
    private Id coderId;
    private DateTime expiredAt;    
}
