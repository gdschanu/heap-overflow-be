package hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.forgotPasswordCode;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.ForgotPasswordCode;
import hanu.gdsc.share.domains.Id;

public interface ForgotPasswordCodeRepository {
    public void save(ForgotPasswordCode forgotPasswordCode);
    public ForgotPasswordCode getByCoderId(Id coderId);
}
