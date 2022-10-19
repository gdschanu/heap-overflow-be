package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.NotFoundException;
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
