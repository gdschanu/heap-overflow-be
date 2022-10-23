package hanu.gdsc.infrastructure.coderAuth.repositories.registerVerificationCode;

import java.lang.reflect.Constructor;

import javax.persistence.*;
import hanu.gdsc.domain.coderAuth.models.RegisterVerificationCode;
import hanu.gdsc.domain.share.models.DateTime;
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
                hanu.gdsc.domain.share.models.Id.class,
                String.class,
                DateTime.class
            );
            con.setAccessible(true);
            return con.newInstance(
                new hanu.gdsc.domain.share.models.Id(coderId),
                code,
                new DateTime(expireAt)
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
