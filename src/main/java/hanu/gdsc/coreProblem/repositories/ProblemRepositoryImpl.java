package hanu.gdsc.coreProblem.repositories;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coreProblem.domains.*;
import hanu.gdsc.coreProblem.repositories.JPA.ProblemJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.*;
import hanu.gdsc.share.domains.Id;

@Repository
public class ProblemRepositoryImpl implements ProblemRepository{
    @Autowired
    private ProblemJPARepository problemJPARepository;
    
    @Override
    public Problem getById(Id id) {
        ProblemEntity problemEntity = problemJPARepository.getById(id.toUUID());
        if(problemEntity == null) {
            return null;
        }
        return ProblemEntity.toDomain(problemEntity);
    }

    @Override
    public void create(Problem problem) {
        problemJPARepository.save(ProblemEntity.toEntity(problem));
    }

    @Override
    public void deleteAllById(List<Id> ids) {
        problemJPARepository.deleteAllById(ids.stream()
                                        .map(id -> id.toUUID())
                                        .collect(Collectors.toList()));        
    }

    @Override
    public List<Problem> search (Pageable pageable) {
        List<ProblemEntity> problemsEntity = problemJPARepository.findAll(pageable).getContent();
        return problemsEntity.stream()
                        .map(problemEntity -> ProblemEntity.toDomain(problemEntity))
                        .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return problemJPARepository.count();
    }

    
}
