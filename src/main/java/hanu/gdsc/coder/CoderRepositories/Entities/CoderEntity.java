package hanu.gdsc.coder.CoderRepositories.Entities;

import java.sql.Blob;

import javax.persistence.Entity;

import hanu.gdsc.coder.Domains.Gender;
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
public class CoderEntity {
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
