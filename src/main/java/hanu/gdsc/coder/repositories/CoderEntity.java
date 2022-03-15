package hanu.gdsc.coder.repositories;

import java.sql.Blob;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import hanu.gdsc.coder.domains.Coder;
import hanu.gdsc.coder.domains.Gender;

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
    private String name;
    private String avatar;
    private String phone;
    private String university;
    private String slogan;
    private Gender gender;
    private String address;

    public static CoderEntity fromDomains(Coder coder) {
        throw new Error("Unimplemented");
    }
}
