package hanu.gdsc.coder.CoderRepositories.Entities;

import java.sql.Blob;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import hanu.gdsc.coder.Domains.Gender;

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
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String username;
    private Blob avatar;
    private String email;
    private String phone;
    private String university;
    private String slogan;
    private Gender gender;
    private String address;
}
