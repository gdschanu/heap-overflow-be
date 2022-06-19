package hanu.gdsc.core_discussion.services.post;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface UpdatePostService {
    @AllArgsConstructor
    @Getter
    public static class Input {
        private Id id;
        private String serviceToCreate;

        private String title;
        private String content;
    }

    public Id execute(Input input);
}
