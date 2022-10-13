package hanu.gdsc.coderAuth.repositories.user;

import hanu.gdsc.coderAuth.domains.Email;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.domains.Username;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface UserRepository {
    public User getByUsername(Username username);
    public User getByEmail(Email email);
    public void save(User user);
    public User getByCoderId(Id coderId);

    public List<User> getByCoderIds(List<Id> coderIds);
}
