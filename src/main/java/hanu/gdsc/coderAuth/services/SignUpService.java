package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.Password;
import hanu.gdsc.coderAuth.domains.Username;

public interface SignUpService {
    public boolean signUp(Email email, Username username, Password password);
}
