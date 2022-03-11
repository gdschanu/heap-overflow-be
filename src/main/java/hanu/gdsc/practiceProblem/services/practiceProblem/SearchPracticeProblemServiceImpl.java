package hanu.gdsc.practiceProblem.services.practiceProblem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.services.problem.SearchProblemService;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.PracticeProblemRepository;
import hanu.gdsc.share.domains.Id;

@Service
public class SearchPracticeProblemServiceImpl implements SearchPracticeProblemService{
    @Autowired
    private PracticeProblemRepository practiceProblemRepository;
    @Autowired
    private SearchProblemService searchProblemService;

    @Override
    public Output getById(Id practiceProblemId) {
        Problem practiceProblem = practiceProblemRepository.getById(practiceProblemId);
        hanu.gdsc.coreProblem.domains.Problem coreProblem = searchProblemService.getById(practiceProblem.getCoreProlemId());
        Output.builder()
            .practiceProblem(practiceProblem)
            .coreProblem(coreProblem)
            .build();
        return null;
    }
    
}
