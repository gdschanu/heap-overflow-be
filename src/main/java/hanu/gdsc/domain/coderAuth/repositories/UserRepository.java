package hanu.gdsc.domain.coderAuth.repositories;

import hanu.gdsc.domain.coderAuth.models.Email;
import hanu.gdsc.domain.coderAuth.models.User;
import hanu.gdsc.domain.coderAuth.models.Username;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface UserRepository {
    public User getByUsername(Username username);
    public User getByEmail(Email email);
    public void save(User user);
    public User getByCoderId(Id coderId);

    public List<User> getByCoderIds(List<Id> coderIds);
}
