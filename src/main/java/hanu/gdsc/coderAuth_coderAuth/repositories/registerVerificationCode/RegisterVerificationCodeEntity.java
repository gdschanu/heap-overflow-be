package hanu.gdsc.coderAuth_coderAuth.repositories.registerVerificationCode;

import java.lang.reflect.Constructor;

import javax.persistence.*;
import hanu.gdsc.coderAuth_coderAuth.domains.RegisterVerificationCode;
import lombok.*;

import javax.persistence.Id;

@Entity
@Table(name = "coder_auth_register_verification_code")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterVerificationCodeEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;
    private String code;
    private String expireAt;

    public static RegisterVerificationCodeEntity toEntity(RegisterVerificationCode registerVerificationCode) {
        return RegisterVerificationCodeEntity.builder()
            .coderId(registerVerificationCode.getCoderId().toString())
            .code(registerVerificationCode.getCode())
            .expireAt(registerVerificationCode.getExpireAt().toString())
            .build()
        ;
    }

    public RegisterVerificationCode toDomain() {
        try {
            Constructor<RegisterVerificationCode> con = RegisterVerificationCode.class.getDeclaredConstructor(
                hanu.gdsc.share.domains.Id.class,
                String.class,
                hanu.gdsc.share.domains.DateTime.class
            );
            con.setAccessible(true);
            return con.newInstance(
                new hanu.gdsc.share.domains.Id(coderId),
                code,
                new hanu.gdsc.share.domains.DateTime(expireAt)
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
