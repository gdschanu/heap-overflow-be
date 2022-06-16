package hanu.gdsc.coderAuth_coderAuth.services;

public interface ChangePasswordService {
    public void changePassword(String token, String oldPassword, String newPassword);
}
