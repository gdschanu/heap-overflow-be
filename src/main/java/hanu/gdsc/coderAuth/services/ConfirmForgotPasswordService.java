package hanu.gdsc.coderAuth.services;

public interface ConfirmForgotPasswordService {
    public void confirmForgotPassword(String email, String code, String newPassword);
    
}
