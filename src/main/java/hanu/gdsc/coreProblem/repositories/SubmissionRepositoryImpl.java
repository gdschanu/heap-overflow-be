package hanu.gdsc.coreProblem.repositories;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.coreProblem.repositories.JPA.SubmissionJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.SubmissionEntity;
import hanu.gdsc.share.domains.Id;

@Repository
public class SubmissionRepositoryImpl implements SubmissionRepository {
    @Autowired
    private SubmissionJPARepository submissionJPARepository;

    @Override
    public void create(Submission submission) {
        submissionJPARepository.save(SubmissionEntity.toEntity(submission));
    }

    @Override
    public List<Submission> get(int page, int perPage, Id problemId, Id coderId, String serviceToCreate) {
        Pageable pageable = PageRequest.of(page, perPage);
        Page<SubmissionEntity> submissionsEntity = submissionJPARepository.get(problemId.toString(), coderId.toString(), serviceToCreate, pageable);
        return submissionsEntity.getContent().stream()
                .map(s -> SubmissionEntity.toDomain(s))
                .collect(Collectors.toList());
    }

    @Override
    public Submission getById(Id id, String serviceToCreate) {
        try {
            SubmissionEntity submissionEntity = submissionJPARepository.getByIdAndServiceToCreate(id.toString(), serviceToCreate);
            return SubmissionEntity.toDomain(submissionEntity);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

}
