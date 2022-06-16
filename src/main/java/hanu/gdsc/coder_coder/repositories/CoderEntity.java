package hanu.gdsc.coder_coder.repositories;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import hanu.gdsc.coder_coder.domains.Coder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coder_coder")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoderEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    private String name;
    private String avatar;
    private String phone;
    private String university;
    private String slogan;
    private String gender;
    private String address;

    public static CoderEntity fromDomains(Coder coder) {  
        return CoderEntity.builder()
        .id(coder.getId().toString())
        .name(coder.getName())
        .avatar(coder.getAvatar() == null ? null : coder.getAvatar().toString())
        .phone(coder.getPhone() == null ? null : coder.getPhone().toString())
        .university(coder.getUniversity())
        .slogan(coder.getSlogan())
        .gender(coder.getGender() == null ? null : coder.getGender().toString())
        .address(coder.getAddress())
        .build();
    }
}
