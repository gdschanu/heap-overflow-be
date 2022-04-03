package hanu.gdsc.coreProblem.repositories;

import hanu.gdsc.coreProblem.domains.SubmissionCount;
import hanu.gdsc.coreProblem.repositories.JPA.SubmissionCountJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.SubmissionCountEntity;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public class SubmissionCountRepositoryImpl implements SubmissionCountRepository {
    @Autowired
    private SubmissionCountJPARepository submissionCountJPARepository;

    @Override
    public void create(SubmissionCount submissionCount) {
        submissionCountJPARepository.save(SubmissionCountEntity.toEntity(submissionCount));
    }

    @Override
    public void update(SubmissionCount submissionCount) {
        submissionCountJPARepository.update(
                new Long(submissionCount.getACsCount()),
                new Long(submissionCount.getSubmissionsCount()),
                submissionCount.getProblemId().toString(),
                new Long(submissionCount.getVersion())
        );
    }

    @Override
    public SubmissionCount getByProblemId(Id problemId, String serviceToCreate) {
        try {
            SubmissionCountEntity submissionCountEnt = submissionCountJPARepository
                    .findByProblemIdAndServiceToCreate(problemId.toString(), serviceToCreate);
            return SubmissionCountEntity.toDomain(submissionCountEnt);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

}
