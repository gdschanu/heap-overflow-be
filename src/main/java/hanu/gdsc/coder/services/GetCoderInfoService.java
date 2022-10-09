package hanu.gdsc.coder.services;

import hanu.gdsc.coder.domains.Coder;
import hanu.gdsc.coder.repositories.CoderRepository;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCoderInfoService {

    @Autowired
    private CoderRepository coderRepository;
    
    @Autowired
    private UserRepository userRepository;

    public OutputInfo getCoderInfo(Id id) throws InvalidInputException {
        return toOutputInfo(coderRepository.getById(id));
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
}
