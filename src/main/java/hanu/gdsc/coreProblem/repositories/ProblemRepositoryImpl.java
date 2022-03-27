package hanu.gdsc.coreProblem.repositories;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.repositories.JPA.ProblemJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.ProblemEntity;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Repository
public class ProblemRepositoryImpl implements ProblemRepository {
    @Autowired
    private ProblemJPARepository problemJPARepository;

    @Override
    public Problem getById(Id id) {
        try {
            ProblemEntity problemEntity = problemJPARepository.getById(id.toString());
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
    public void update(Problem problem) {
        problemJPARepository.save(ProblemEntity.toEntity(problem));
    }

    @Override
    public void deleteAllById(List<Id> ids) {
        problemJPARepository.deleteAllById(ids.stream()
                .map(id -> id.toString())
                .collect(Collectors.toList()));
    }

    @Override
    public List<Problem> search(Pageable pageable) {
        List<ProblemEntity> problemsEntity = problemJPARepository.findAll(pageable).getContent();
        return problemsEntity.stream()
                .map(problemEntity -> ProblemEntity.toDomain(problemEntity))
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return problemJPARepository.count();
    }

    @Override
    public List<Problem> getByIds(List<Id> ids) {
        List<ProblemEntity> entities = problemJPARepository
                .findByIdIn(ids.stream().map(x -> x.toString())
                        .collect(Collectors.toList()));
        return entities.stream().map(x -> ProblemEntity.toDomain(x))
                .collect(Collectors.toList());
    }


}
