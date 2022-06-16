package hanu.gdsc.coderAuth_coderAuth.services;

import hanu.gdsc.share.domains.Id;

public interface ConfirmRegisterVerificationCodeService {
    public void confirmRegisterVerificationCode(String code, Id coderId);
}
