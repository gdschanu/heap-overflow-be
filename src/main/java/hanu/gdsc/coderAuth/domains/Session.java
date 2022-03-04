package hanu.gdsc.coderAuth.domains;

import java.time.ZonedDateTime;

import javax.persistence.Entity;

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
public class Session {
    private Id id;
    private Id coderId;
    private ZonedDateTime expiredAt;    
}
