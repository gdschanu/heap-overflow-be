package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.Password;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.share.domains.Id;

public interface LogInService {
    public String checkUserInformation(Object usernameOrEmail, Password password);
    public Object usernameOrEmail(User user);
    public  String createToken(Id coderId);
}
