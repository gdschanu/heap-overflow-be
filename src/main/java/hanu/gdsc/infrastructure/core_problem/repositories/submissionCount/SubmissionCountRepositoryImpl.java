package hanu.gdsc.infrastructure.core_problem.repositories.submissionCount;

import hanu.gdsc.domain.core_problem.models.SubmissionCount;
import hanu.gdsc.domain.core_problem.repositories.SubmissionCountRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SubmissionCountRepositoryImpl implements SubmissionCountRepository {
    @Autowired
    private SubmissionCountJPARepository submissionCountJPARepository;

    @Override
    public void create(SubmissionCount submissionCount) {
        submissionCountJPARepository.save(SubmissionCountEntity.toEntity(submissionCount));
    }

    @Override
    public void createMany(List<SubmissionCount> submissions) {
        submissionCountJPARepository.saveAll(submissions.stream()
                .map(submission -> SubmissionCountEntity.toEntity(submission))
                .collect(Collectors.toList()));
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

    @Override
    public SubmissionCount getByProblemId(Id problemId) {
        try {
            SubmissionCountEntity ent = submissionCountJPARepository.findByProblemId(problemId.toString());
            return SubmissionCountEntity.toDomain(ent);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public List<SubmissionCount> getByProblemIds(List<Id> problemIds, String serviceToCreate) {
        return submissionCountJPARepository.findByProblemIdInAndServiceToCreate(
                        problemIds.stream()
                                .map(id -> id.toString())
                                .collect(Collectors.toList()),
                        serviceToCreate
                ).stream()
                .map(ent -> SubmissionCountEntity.toDomain(ent))
                .collect(Collectors.toList());
    }

}
