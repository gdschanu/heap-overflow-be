package hanu.gdsc.core_like.services.reaction;

import hanu.gdsc.core_like.domains.Reaction;
import hanu.gdsc.share.domains.Id;
import lombok.*;

public interface SearchReactionService {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id coderId;
        public Id reactedObjectId;
    }

    public Reaction getByCoderId(Input input);
}
