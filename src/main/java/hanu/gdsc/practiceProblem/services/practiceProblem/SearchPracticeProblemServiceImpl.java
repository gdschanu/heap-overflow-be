package hanu.gdsc.practiceProblem.services.practiceProblem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.services.problem.SearchProblemService;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.PracticeProblemRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

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
        if (practiceProblem == null || coreProblem == null) {
            throw new BusinessLogicError("Không tìm thấy bài toán phù hợp", "NOT_FOUND");
        }
        return Output.builder()
            .practiceProblem(practiceProblem)
            .coreProblem(coreProblem)
            .build();
    }
    
}
