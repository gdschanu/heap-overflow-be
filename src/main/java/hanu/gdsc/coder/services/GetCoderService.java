package hanu.gdsc.coder.services;

import hanu.gdsc.coder.domains.Coder;
import hanu.gdsc.coder.repositories.CoderRepository;
import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCoderService {

    @Autowired
    private CoderRepository coderRepository;
    
    @Autowired
    private UserRepository userRepository;

    public OutputInfo getCoderInfo(Id id) {
        return toOutputInfo(coderRepository.getById(id));
    }

    public OutputAccount getCoderAccount(Id id) {
        return toOutputAccount(userRepository.getByCoderId(id));
    }

    @Builder
    public static class OutputInfo {
        public String coderId;
        public String name;
        public int age;
        public String university;
        public String slogan;
        public String avatar;
        public String gender;
        public String phone;
        public String address;
    }

    private static OutputInfo toOutputInfo(Coder coder) {
        return OutputInfo.builder()
                .coderId(coder.getId().toString())
                .name(coder.getName())
                .age(coder.getAge())
                .university(coder.getUniversity())
                .slogan(coder.getSlogan())
                .avatar(coder.getAvatar() == null ? null : coder.getAvatar().toString())
                .gender(coder.getGender() == null ? null : coder.getGender().toString())
                .phone(coder.getPhone() == null ? null : coder.getPhone().toString())
                .address(coder.getAddress()).build();
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
