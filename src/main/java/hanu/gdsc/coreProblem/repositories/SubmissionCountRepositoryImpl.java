package hanu.gdsc.coreProblem.repositories;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coreProblem.domains.SubmissionCount;
import hanu.gdsc.coreProblem.repositories.JPA.SubmissionCountJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.SubmissionCountEntity;
import hanu.gdsc.share.domains.Id;

@Repository
public class SubmissionCountRepositoryImpl implements SubmissionCountRepository{
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
        submissionCountJPARepository.save(SubmissionCountEntity.toEntity(submissionCount));
    }
    
}
