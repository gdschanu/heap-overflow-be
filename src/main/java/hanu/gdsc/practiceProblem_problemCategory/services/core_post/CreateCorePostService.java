package hanu.gdsc.practiceProblem_problemCategory.services.core_post;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface CreateCorePostService {
    @Getter
    public static class Input {
        private String title;
        private Id author;
        private String content;

        public Input(String title, Id author, String content) {
            this.title = title;
            this.author = author;
            this.content = content;
        }
    }

    public Id create(Input input);
}
