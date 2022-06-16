package hanu.gdsc.coderAuthSubdomain.coderAuthContext.services;

public interface ChangePasswordService {
    public void changePassword(String token, String oldPassword, String newPassword);
}
