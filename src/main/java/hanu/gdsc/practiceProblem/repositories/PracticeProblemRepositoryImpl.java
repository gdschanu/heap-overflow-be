package hanu.gdsc.practiceProblem.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.JPA.PracticeProblemJPARepository;
import hanu.gdsc.practiceProblem.repositories.entities.PracticeProblemEntity;

@Repository
public class PracticeProblemRepositoryImpl implements PracticeProblemRepository{
    @Autowired
    private PracticeProblemJPARepository practiceProblemJpaRepository;

    @Override
    public void create(Problem practiceProblem) {
        practiceProblemJpaRepository.save(PracticeProblemEntity.toEntity(practiceProblem));
    }
}
