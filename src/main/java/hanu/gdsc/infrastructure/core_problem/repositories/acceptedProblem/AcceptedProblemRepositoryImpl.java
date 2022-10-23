package hanu.gdsc.infrastructure.core_problem.repositories.acceptedProblem;

import hanu.gdsc.domain.core_problem.models.AcceptedProblem;
import hanu.gdsc.domain.core_problem.repositories.AcceptedProblemRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AcceptedProblemRepositoryImpl implements AcceptedProblemRepository {
    @Autowired
    private AcceptedProblemJPARepository jpaRepository;

    @Override
    public void save(AcceptedProblem acceptedProblem) {
        jpaRepository.save(AcceptedProblemEntity.fromDomain(acceptedProblem));
    }

    @Override
    public List<AcceptedProblem> getByProblemIdsAndCoderId(Id coderId, List<Id> problemIds) {
        return jpaRepository.findAllById(problemIds.stream()
                        .map(problemId -> AcceptedProblemEntity.genId(coderId, problemId))
                        .collect(Collectors.toList())).stream()
                .map(entity -> entity.toDomain())
                .collect(Collectors.toList());
    }

    @Override
    public AcceptedProblem getByProblemIdAndCoderId(Id problemId, Id coderId, String serviceToCreate) {
        return null;
    }
}
