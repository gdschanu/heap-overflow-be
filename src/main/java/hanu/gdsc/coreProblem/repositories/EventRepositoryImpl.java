package hanu.gdsc.coreProblem.repositories;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coreProblem.repositories.JPA.EventJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.EventEntity;

@Repository
public class EventRepositoryImpl implements EventRepository {
    @Autowired
    private EventJPARepository eventJPARepository;

    @Override
    public EventEntity getEventEntity() {
        try {
            return eventJPARepository.getEventEntity();
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Input input) {
        EventEntity eventEntity = EventEntity.builder()
            .problemId(input.problemId.toString())
            .status(input.status.toString())
            .build();
        eventJPARepository.save(eventEntity);
    }

    @Override
    public void delete(long id) {
        eventJPARepository.deleteById(id);
    }
}
