package hanu.gdsc.core_problem.repositories.submissionEvent;

import hanu.gdsc.core_problem.domains.SubmissionEvent;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class SubmissionEventRepositoryImpl implements SubmissionEventRepository {
    @Autowired
    private EventJPARepository eventJPARepository;

    @Override
    public SubmissionEvent getSubmissionEvent() {
        Page<SubmissionEventEntity> eventEntities = eventJPARepository
                .findAll(Pageable.ofSize(1));
        if (eventEntities.isEmpty()) {
            return null;
        }
        return SubmissionEventEntity.toDomain(eventEntities.getContent().get(0));
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
