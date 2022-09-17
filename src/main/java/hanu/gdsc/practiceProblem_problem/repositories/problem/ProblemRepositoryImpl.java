package hanu.gdsc.practiceProblem_problem.repositories.problem;

import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
            PPProblemEntity PPProblemEntity = PPProblemJpaRepository.getById(id.toString());
            return PPProblemEntity.toDomain(PPProblemEntity);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Problem> get(int page, int perPage) {
        Pageable pageable = PageRequest.of(page , perPage, Sort.by("createdAt").descending());
        Page<PPProblemEntity> entities =  PPProblemJpaRepository.findAll(pageable);
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
}
