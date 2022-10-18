package hanu.gdsc.core_problem.services.acceptedProblem;

import hanu.gdsc.core_problem.domains.AcceptedProblem;
import hanu.gdsc.core_problem.repositories.acceptedProblem.AcceptedProblemRepository;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchAcceptedProblemServiceImpl implements SearchAcceptedProblemService {
    private AcceptedProblemRepository acceptedProblemRepository;

    @Override
    public List<AcceptedProblem> getByProblemIdsAndCoderId(Id coderId, List<Id> problemIds, String serviceToCreate) {
        return acceptedProblemRepository.getByProblemIdsAndCoderId(coderId, problemIds);
    }

    @Override
    public AcceptedProblem getByProblemIdAndCoderId(Id problemId, Id coderId, String serviceToCreate) {
        return acceptedProblemRepository.getByProblemIdAndCoderId(problemId, coderId, serviceToCreate);
    }
}
