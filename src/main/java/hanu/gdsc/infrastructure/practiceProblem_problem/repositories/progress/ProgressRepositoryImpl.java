package hanu.gdsc.infrastructure.practiceProblem_problem.repositories.progress;

import hanu.gdsc.domain.practiceProblem_problem.models.Progress;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProgressRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProgressRepositoryImpl implements ProgressRepository {
    @Autowired
    private ProgressJpaRepository progressJpaRepository;

    @Override
    public Progress getByCoderId(Id coderId) {
        ProgressEntity entity = progressJpaRepository.findByCoderId(coderId.toString());
        if (entity == null) return null;
        return entity.toDomain();
    }

    @Override
    public void save(Progress progress) {
        progressJpaRepository.save(ProgressEntity.fromDomain(progress));
    }
}
