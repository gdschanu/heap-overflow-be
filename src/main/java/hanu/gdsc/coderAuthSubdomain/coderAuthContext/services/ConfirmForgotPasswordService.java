package hanu.gdsc.coderAuthSubdomain.coderAuthContext.services;

public interface ConfirmForgotPasswordService {
    public void confirmForgotPassword(String email, String code, String newPassword);
    
}
