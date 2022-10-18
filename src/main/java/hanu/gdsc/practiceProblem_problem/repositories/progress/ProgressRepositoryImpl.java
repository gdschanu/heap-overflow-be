package hanu.gdsc.practiceProblem_problem.repositories.progress;

import hanu.gdsc.practiceProblem_problem.domains.Progress;
import hanu.gdsc.share.domains.Id;
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
