package hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.forgotPasswordCode;

import java.lang.reflect.Constructor;
import javax.persistence.*;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.ForgotPasswordCode;
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
