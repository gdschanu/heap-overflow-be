package hanu.gdsc.infrastructure.coderAuth.repositories.user;

import hanu.gdsc.domain.coderAuth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.domain.coderAuth.models.Email;
import hanu.gdsc.domain.coderAuth.models.User;
import hanu.gdsc.domain.coderAuth.models.Username;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserJPARepository userJPARepository;

    @Override
    public User getByUsername(Username username) {
        UserEntity userEntity = userJPARepository.getByUsername(username.toString());
        User user = null;
        if(userEntity != null) {
            user = userEntity.toDomain();
        } 
        return user;
    }

    @Override
    public User getByEmail(Email email) {
        UserEntity userEntity = userJPARepository.getByEmail(email.toString());
        User user = null;
        if(userEntity != null) {
            user = userEntity.toDomain();
        }
        return user;
    }

    @Override
    public void save(User user) {
        userJPARepository.save(UserEntity.toEntity(user));
    }

    @Override
    public User getByCoderId(Id coderId) {
        return userJPARepository.getByCoderId(coderId.toString()).toDomain();
    }

    @Override
    public List<User> getByCoderIds(List<Id> coderIds) {
        List<User> users = userJPARepository.getByCoderIds
                (coderIds.stream().map(x -> x.toString()).collect(Collectors.toList()))
                .stream().map(x -> x.toDomain()).collect(Collectors.toList());
        return users;
    }

}
