package hanu.gdsc.core_problem.repositories.runningSubmission;

import hanu.gdsc.core_problem.domains.RunningSubmission;
import hanu.gdsc.practiceProblem_problem.config.RunningSubmissionConfig;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RunningSubmissionRepositoryImpl implements RunningSubmissionRepository {
    @Autowired
    private RunningSubmissionJPARepository runningSubmissionJPARepository;

    @Override
    public void create(RunningSubmission runningSubmission) {
        runningSubmissionJPARepository.save(RunningSubmissionEntity
                .fromDomain(runningSubmission,
                        0,
                        System.currentTimeMillis() - 999999));
    }

    private long getLockedUntil() {
        return System.currentTimeMillis() + RunningSubmissionConfig.CLAIM_SUBMISSION_LOCK_SECOND * 1000;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public RunningSubmission claim() {
        RunningSubmissionEntity runningSubmission = runningSubmissionJPARepository.claim(System.currentTimeMillis());
        if (runningSubmission == null) {
            return null;
        }
        runningSubmission.setLocked(1);
        runningSubmission.setLockedUntil(getLockedUntil());
        runningSubmissionJPARepository.save(runningSubmission);
        RunningSubmission domain = runningSubmission.toDomain();
        domain.increaseVersion();
        return domain;
    }

    @Override
    public void delete(Id id) {
        runningSubmissionJPARepository.customDelete(id.toString());
    }


    @Override
    public void updateClaimed(RunningSubmission runningSubmission) {
        RunningSubmissionEntity entity = RunningSubmissionEntity.fromDomain(runningSubmission, 1, getLockedUntil());
        runningSubmissionJPARepository.save(entity);
        runningSubmission.increaseVersion();
    }

    @Override
    public RunningSubmission getById(Id id, String serviceToCreate) {
        try {
            RunningSubmissionEntity entity = runningSubmissionJPARepository.findByIdAndServiceToCreate(id.toString(), serviceToCreate);
            if (entity == null) {
                return null;
            }
            return entity.toDomain();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<RunningSubmission> getByProblemIdAndCoderId(Id problemId,
                                                            Id coderId,
                                                            int page,
                                                            int perPage,
                                                            String serviceToCreate) {
        Pageable pageable = PageRequest.of(page, perPage);
        Page<RunningSubmissionEntity> entities = null;
        if (problemId == null && coderId == null) {
            entities = runningSubmissionJPARepository
                    .findByServiceToCreate(
                            serviceToCreate,
                            pageable);
        } else if (problemId == null && coderId != null) {
            entities = runningSubmissionJPARepository
                    .findByCoderIdAndServiceToCreate(
                            coderId.toString(),
                            serviceToCreate,
                            pageable);
        } else if (problemId != null && coderId == null) {
            entities = runningSubmissionJPARepository
                    .findByProblemIdAndServiceToCreate(
                            problemId.toString(),
                            serviceToCreate,
                            pageable);
        } else if (problemId != null && coderId != null) {
            entities = runningSubmissionJPARepository
                    .findByProblemIdAndCoderIdAndServiceToCreate(
                            problemId.toString(),
                            coderId.toString(),
                            serviceToCreate,
                            pageable);
        }
        return entities.getContent()
                .stream()
                .map(e -> e.toDomain())
                .collect(Collectors.toList());
    }
}
