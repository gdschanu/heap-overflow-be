package hanu.gdsc.infrastructure.coderAuth.repositories.forgotPasswordCode;

import java.lang.reflect.Constructor;
import javax.persistence.*;
import hanu.gdsc.domain.coderAuth.models.ForgotPasswordCode;
import hanu.gdsc.domain.share.models.DateTime;
import lombok.*;

import javax.persistence.Id;

@Entity
@Table(name = "coder_auth_forgot_password_code")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ForgotPasswordCodeEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;
    private String code;
    private String expireAt;

    public static ForgotPasswordCodeEntity toEntity(ForgotPasswordCode forgotPasswordCode) {
        return ForgotPasswordCodeEntity.builder()
            .coderId(forgotPasswordCode.getCoderId().toString())
            .code(forgotPasswordCode.getCode())
            .expireAt(forgotPasswordCode.getExpireAt().toString())
            .build()
        ;
    }

    public ForgotPasswordCode toDomain() {
        try {
            Constructor<ForgotPasswordCode> con = ForgotPasswordCode.class.getDeclaredConstructor(
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
