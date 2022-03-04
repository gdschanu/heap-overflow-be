package hanu.gdsc.coder.Domains;

import java.sql.Blob;

import javax.persistence.Entity;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coder {
    private Id id;
    private String username;
    private Blob avatar;
    private String email;
    private String phone;
    private String university;
    private String slogan;
    private Gender gender;
    private String address;
}
