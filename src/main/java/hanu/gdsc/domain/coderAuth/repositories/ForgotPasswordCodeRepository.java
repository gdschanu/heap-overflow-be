package hanu.gdsc.domain.coderAuth.repositories;

import hanu.gdsc.domain.coderAuth.models.ForgotPasswordCode;
import hanu.gdsc.domain.share.models.Id;

public interface ForgotPasswordCodeRepository {
    public void save(ForgotPasswordCode forgotPasswordCode);
    public ForgotPasswordCode getByCoderId(Id coderId);
}
