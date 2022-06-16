package hanu.gdsc.coderAuthSubdomain.coderAuthContext.repositories.user;

import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.Email;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.User;
import hanu.gdsc.coderAuthSubdomain.coderAuthContext.domains.Username;
import hanu.gdsc.share.domains.Id;

public interface UserRepository {
    public User getByUsername(Username username);
    public User getByEmail(Email email);
    public void save(User user);
    public User getByCoderId(Id coderId);
}
