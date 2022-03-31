package hanu.gdsc.coreProblem.repositories;

import hanu.gdsc.coreProblem.domains.Status;
import hanu.gdsc.coreProblem.repositories.entities.EventEntity;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface EventRepository {
    @Builder
    public static class Input {
        public Id problemId;
        public Status status;
    }
    
    public EventEntity getEventEntity();
    public void create(Input input);
    public void delete(long id);
}
