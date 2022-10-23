package hanu.gdsc.domain.coderAuth.services.user;

import hanu.gdsc.domain.coderAuth.models.User;
import hanu.gdsc.domain.coderAuth.repositories.UserRepository;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchUserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> getListUserByCoderIds(List<Id> coderIds) {
        List<User> users = userRepository.getByCoderIds(coderIds);
        return users;
    }

    public User getByCoderId(Id coderId) throws NotFoundException {
        User user = userRepository.getByCoderId(coderId);
        if (user == null) {
            throw new NotFoundException("Unknown user");
        }
        return user;
    }
}
