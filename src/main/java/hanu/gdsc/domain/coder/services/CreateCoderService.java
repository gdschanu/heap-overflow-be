package hanu.gdsc.domain.coder.services;

import hanu.gdsc.domain.coder.models.Gender;
import hanu.gdsc.domain.coder.models.Phone;
import hanu.gdsc.domain.coder.models.Url;
import hanu.gdsc.domain.share.models.Id;
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
