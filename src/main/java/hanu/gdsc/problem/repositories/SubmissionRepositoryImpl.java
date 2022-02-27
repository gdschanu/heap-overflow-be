package hanu.gdsc.problem.repositories;

import hanu.gdsc.problem.domains.Submission;
import hanu.gdsc.problem.repositories.JPA.SubmissionJPARepository;
import hanu.gdsc.problem.repositories.entities.SubmissionEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class SubmissionRepositoryImpl implements SubmissionRepository {
    @Autowired
    private SubmissionJPARepository submissionJPARepository;

    @Override
    public void save(Submission submission) {
        submissionJPARepository.save(SubmissionEntity.fromDomainObject(submission));
    }
}
