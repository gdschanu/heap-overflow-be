package hanu.gdsc.coderAuth_coderAuth.services;

public interface ConfirmForgotPasswordService {
    public void confirmForgotPassword(String email, String code, String newPassword);
    
}
