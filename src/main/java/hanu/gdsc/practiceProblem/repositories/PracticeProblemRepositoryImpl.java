package hanu.gdsc.practiceProblem.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.JPA.PracticeProblemJPARepository;
import hanu.gdsc.practiceProblem.repositories.entities.PracticeProblemEntity;
import hanu.gdsc.share.domains.Id;

@Repository
public class PracticeProblemRepositoryImpl implements PracticeProblemRepository{
    @Autowired
    private PracticeProblemJPARepository practiceProblemJpaRepository;

    @Override
    public void create(Problem practiceProblem) {
        practiceProblemJpaRepository.save(PracticeProblemEntity.toEntity(practiceProblem));
    }

    @Override
    public Problem getById(Id id) {
        PracticeProblemEntity practiceProblemEntity = practiceProblemJpaRepository.getById(id.toUUID());
        return PracticeProblemEntity.toDomain(practiceProblemEntity);
    }
}
