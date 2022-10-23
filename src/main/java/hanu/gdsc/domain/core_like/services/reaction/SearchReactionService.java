package hanu.gdsc.domain.core_like.services.reaction;

import hanu.gdsc.domain.core_like.models.Reaction;
import hanu.gdsc.domain.share.models.Id;
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
