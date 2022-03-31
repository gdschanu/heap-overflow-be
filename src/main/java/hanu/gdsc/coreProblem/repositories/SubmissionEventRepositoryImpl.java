package hanu.gdsc.coreProblem.repositories;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coreProblem.domains.SubmissionEvent;
import hanu.gdsc.coreProblem.repositories.JPA.EventJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.SubmissionEventEntity;
import hanu.gdsc.share.domains.Id;

@Repository
public class SubmissionEventRepositoryImpl implements SubmissionEventRepository {
    @Autowired
    private EventJPARepository eventJPARepository;

    @Override
    public SubmissionEvent getSubmissionEvent() {
        try {
            SubmissionEventEntity submissionEventEntity = eventJPARepository.getEventEntity();
            return SubmissionEventEntity.toDomain(submissionEventEntity);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(SubmissionEvent submissionEvent) {
        eventJPARepository.save(SubmissionEventEntity.toEntity(submissionEvent));
    }

    @Override
    public void delete(Id id) {
        eventJPARepository.deleteById(id.toString());
    }
}
