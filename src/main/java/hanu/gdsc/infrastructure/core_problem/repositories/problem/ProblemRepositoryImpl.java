package hanu.gdsc.infrastructure.core_problem.repositories.problem;

import hanu.gdsc.domain.core_problem.models.Problem;
import hanu.gdsc.domain.core_problem.repositories.ProblemRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProblemRepositoryImpl implements ProblemRepository {
    @Autowired
    private ProblemJPARepository problemJPARepository;

    @Override
    public Problem getById(Id id, String serviceToCreate) {
        try {
            ProblemEntity problemEntity = problemJPARepository
                    .findByIdAndServiceToCreate(id.toString(), serviceToCreate);
            return ProblemEntity.toDomain(problemEntity);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Problem problem) {
        problemJPARepository.save(ProblemEntity.toEntity(problem));
    }

    @Override
    public void createMany(List<Problem> problems) {
        problemJPARepository.saveAll(problems.stream()
                .map(problem -> ProblemEntity.toEntity(problem))
                .collect(Collectors.toList()));
    }

    @Override
    public void update(Problem problem) {
        problemJPARepository.save(ProblemEntity.toEntity(problem));
    }


    @Override
    public List<Problem> getByIds(List<Id> ids, String serviceToCreate) {
        List<ProblemEntity> entities = problemJPARepository
                .findByIdInAndServiceToCreate(ids.stream().map(x -> x.toString())
                        .collect(Collectors.toList()), serviceToCreate);
        return entities.stream().map(x -> ProblemEntity.toDomain(x))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Id id) {
        problemJPARepository.deleteById(id.toString());
    }

    @Override
    public void deleteByIds(List<Id> ids) {
        problemJPARepository.deleteAllById(
                ids.stream()
                        .map(id -> id.toString())
                        .collect(Collectors.toList())
        );
    }


}
