package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.Password;
import hanu.gdsc.coderAuth.domains.Username;
import hanu.gdsc.share.domains.Id;

public interface LogInService {
    public String checkUserInformation(Email usernameOrEmail, Password password);
    public String checkUserInformation(Username usernameOrEmail, Password password);
    public  String createToken(Id coderId);
}
