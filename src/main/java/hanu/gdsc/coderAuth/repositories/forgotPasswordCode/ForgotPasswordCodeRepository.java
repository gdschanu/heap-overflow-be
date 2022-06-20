package hanu.gdsc.coderAuth.repositories.forgotPasswordCode;

import hanu.gdsc.coderAuth.domains.ForgotPasswordCode;
import hanu.gdsc.share.domains.Id;

public interface ForgotPasswordCodeRepository {
    public void save(ForgotPasswordCode forgotPasswordCode);
    public ForgotPasswordCode getByCoderId(Id coderId);
}
