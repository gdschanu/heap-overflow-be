package hanu.gdsc.infrastructure.practiceProblem_problem.repositories.problem;

import hanu.gdsc.domain.practiceProblem_problem.domains.Problem;
import hanu.gdsc.domain.practiceProblem_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component(value = "PracticeProblem.ProblemRepositoryImpl")
public class ProblemRepositoryImpl implements ProblemRepository {
    @Autowired
    private PPProblemJPARepository PPProblemJpaRepository;

    @Override
    public void create(Problem practiceProblem) {
        PPProblemEntity ppproblemEntity = PPProblemEntity.toEntity(practiceProblem);
        PPProblemJpaRepository.save(ppproblemEntity);
    }

    @Override
    public Problem getById(Id id) {
        try {
            Optional<PPProblemEntity> e = PPProblemJpaRepository.findById(id.toString());
            if (e.isEmpty())
                return null;
            return PPProblemEntity.toDomain(e.get());
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Problem> get(int page, int perPage) {
        Pageable pageable = PageRequest.of(page, perPage, Sort.by("createdAtMillis").descending());
        Page<PPProblemEntity> entities = PPProblemJpaRepository.findAll(pageable);
        return entities.getContent()
                .stream().map(
                        e -> PPProblemEntity.toDomain(e)
                ).collect(Collectors.toList());
    }

    @Override
    public void update(Problem problem) {
        PPProblemEntity ppproblemEntity = PPProblemEntity.toEntity(problem);
        PPProblemJpaRepository.save(ppproblemEntity);
    }

    @Override
    public long countProblem() {
        return PPProblemJpaRepository.count();
    }

    public List<Problem> getRecommendProblem(int count) {
        Pageable pageable = PageRequest.of(0, count);
        Page<PPProblemEntity> entities = PPProblemJpaRepository.getRecommendProblem(pageable);
        return entities.getContent()
                .stream().map(PPProblemEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Id id) {
        PPProblemJpaRepository.deleteById(id.toString());
    }

    @Override
    public Problem getByCoreProblemProblemId(Id coreProblemProblemId) {
        try {
            PPProblemEntity e = PPProblemJpaRepository
                    .findByCoreProblemProblemId(coreProblemProblemId.toString());
            return PPProblemEntity.toDomain(e);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public List<Problem> findByCoreProblemProblemIds(List<Id> coreProblemProblemIds) {
        return PPProblemJpaRepository.findByCoreProblemProblemIdIn(coreProblemProblemIds.stream()
                .map(Id::toString)
                .collect(Collectors.toList())).stream()
                    .map(PPProblemEntity::toDomain)
                    .collect(Collectors.toList());
    }

    @Override
    public List<ProblemCountProjection> countProblemGroupByDifficulty() {
        return PPProblemJpaRepository.countProblemGroupByDifficulty();
    }
}
