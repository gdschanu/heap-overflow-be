package hanu.gdsc.coderAuth.services;

import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCoderAccountService {

    @Autowired
    private UserRepository userRepository;

    public OutputAccount getCoderAccount(Id id) {
        return toOutputAccount(userRepository.getByCoderId(id));
    }

    @Builder
    public static class OutputAccount {
        public String username;
        public String email;
    }

    public static OutputAccount toOutputAccount(User user) {
        return OutputAccount.builder()
                .username(user.getUsername() == null ? null : user.getUsername().toString())
                .email(user.getEmail() == null ? null : user.getEmail().toString()).build();
    }
}
