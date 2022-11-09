package hanu.gdsc.infrastructure.core_problem.repositories.submission;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.core_problem.models.Submission;
import hanu.gdsc.domain.core_problem.repositories.SubmissionRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SubmissionRepositoryImpl implements SubmissionRepository {
    @Autowired
    private SubmissionJPARepository submissionJPARepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public List<Submission> get(int page, int perPage, Id problemId, Id coderId, String serviceToCreate) {
        Pageable pageable = PageRequest.of(page, perPage, Sort.by("submittedAtMillis").descending());
        Page<SubmissionEntity> submissionsEntity;
        if (problemId != null && coderId != null) {
            submissionsEntity = submissionJPARepository.findByProblemIdAndCoderIdAndServiceToCreate(problemId.toString(),
                    coderId.toString(),
                    serviceToCreate,
                    pageable);
        } else if (problemId != null) {
            submissionsEntity = submissionJPARepository.findByProblemIdAndServiceToCreate(problemId.toString(),
                    serviceToCreate,
                    pageable);
        } else if (coderId != null) {
            submissionsEntity = submissionJPARepository.findByCoderIdAndServiceToCreate(coderId.toString(),
                    serviceToCreate,
                    pageable);
        } else {
            submissionsEntity = submissionJPARepository.findByServiceToCreate(serviceToCreate, pageable);
        }
        return submissionsEntity.getContent().stream()
                .map(s -> SubmissionEntity.toDomain(s, objectMapper))
                .collect(Collectors.toList());
    }

    @Override
    public Submission getById(Id id, String serviceToCreate) {
        try {
            SubmissionEntity submissionEntity = submissionJPARepository.getByIdAndServiceToCreate(id.toString(), serviceToCreate);
            if (submissionEntity == null) {
                return null;
            }
            return SubmissionEntity.toDomain(submissionEntity, objectMapper);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public void deleteAllByProblemId(Id problemId) {
        submissionJPARepository.deleteAllByProblemId(problemId.toString());
    }

    @Override
    public Submission getByProblemIdAndCoderId(Id problemId, Id coderId) {
        return submissionJPARepository.findByProblemIdAndCoderId(problemId.toString(), coderId.toString())
                .map(submissionEntity -> SubmissionEntity.toDomain(submissionEntity, objectMapper))
                .orElse(null);
    }

}
