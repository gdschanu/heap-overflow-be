package hanu.gdsc.infrastructure.core_problem.repositories.runningSubmission;

import hanu.gdsc.domain.core_problem.models.RunningSubmission;
import hanu.gdsc.domain.core_problem.repositories.RunningSubmissionRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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

    @Override
    public List<RunningSubmission> getByProblemIdsAndCoderId(List<Id> problemIds, Id coderId, int page, int perPage, String serviceToCreate) {
        Pageable pageable = PageRequest.of(page, perPage);
        Page<RunningSubmissionEntity> entities = null;
        if (problemIds == null && coderId == null) {
            entities = runningSubmissionJPARepository
                    .findByServiceToCreate(
                            serviceToCreate,
                            pageable);
        } else if (problemIds == null && coderId != null) {
            entities = runningSubmissionJPARepository
                    .findByCoderIdAndServiceToCreate(
                            coderId.toString(),
                            serviceToCreate,
                            pageable);
        } else if (problemIds != null && coderId == null) {
            entities = runningSubmissionJPARepository
                    .findByProblemIdInAndServiceToCreate(
                            problemIds.stream()
                                    .map(x -> x.toString())
                                    .collect(Collectors.toList()),
                            serviceToCreate,
                            pageable);
        } else if (problemIds != null && coderId != null) {
            entities = runningSubmissionJPARepository
                    .findByProblemIdInAndCoderIdAndServiceToCreate(
                            problemIds.stream()
                                    .map(x -> x.toString())
                                    .collect(Collectors.toList()),
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
