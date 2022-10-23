package hanu.gdsc.domain.core_discussion.services.post;

import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface UpdatePostService {
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id id;
        public String serviceToCreate;

        public String title;
        public String content;
    }

    public Id execute(Input input);
}
