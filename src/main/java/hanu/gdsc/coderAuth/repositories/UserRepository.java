package hanu.gdsc.coderAuth.repositories;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.domains.Username;

public interface UserRepository {
    public User getByUsername(Username username);
    public User getByEmail(Email email);
    public void save(User user);
}
