package hanu.gdsc.coreProblem.repositories;

import hanu.gdsc.coreProblem.domains.SubmissionCount;
import hanu.gdsc.coreProblem.repositories.JPA.SubmissionCountJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.SubmissionCountEntity;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public class SubmissionCountRepositoryImpl implements SubmissionCountRepository {
    @Autowired
    private SubmissionCountJPARepository submissionCountJPARepository;

    @Override
    public SubmissionCount getById(Id id) {
        try {
            SubmissionCountEntity submissionCountEntity = submissionCountJPARepository.getById(id.toString());
            return SubmissionCountEntity.toDomain(submissionCountEntity);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(SubmissionCount submissionCount) {
        submissionCountJPARepository.save(SubmissionCountEntity.toEntity(submissionCount));
    }

    @Override
    public void update(SubmissionCount submissionCount) {
        submissionCountJPARepository.update(
                new Integer(submissionCount.getACsCount()),
                new Integer(submissionCount.getSubmissionsCount()),
                submissionCount.getProblemId().toString(),
                new Long(submissionCount.getVersion())
        );
    }

    @Override
    public SubmissionCount getByProblemId(Id problemId) {
        List<SubmissionCountEntity> submissionCountEntities = submissionCountJPARepository
                .getByProblemId(problemId.toString());
        if (submissionCountEntities.size() == 0)
            return null;
        return SubmissionCountEntity.toDomain(submissionCountEntities.get(0));
    }

}
