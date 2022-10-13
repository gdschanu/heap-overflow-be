package hanu.gdsc.coder.services;

import hanu.gdsc.coder.domains.Gender;
import hanu.gdsc.coder.domains.Phone;
import hanu.gdsc.coder.domains.Url;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface CreateCoderService {
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Input {
        public String name;
        public int age;
        public Url avatar;
        public Phone phone;
        public String university;
        public String slogan;
        public Gender gender;
        public String address;
    }

    public Id create(Input input);
}
