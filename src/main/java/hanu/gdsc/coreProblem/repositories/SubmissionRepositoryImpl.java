package hanu.gdsc.coreProblem.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.coreProblem.repositories.JPA.SubmissionJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.SubmissionEntity;

@Repository
public class SubmissionRepositoryImpl implements SubmissionRepository {
    @Autowired
    private SubmissionJPARepository submissionJPARepository;

    @Override
    public void save(Submission submission) {
        submissionJPARepository.save(SubmissionEntity.fromDomainObject(submission));
    }
}
