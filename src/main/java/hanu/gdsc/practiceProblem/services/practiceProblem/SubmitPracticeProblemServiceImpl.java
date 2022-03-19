package hanu.gdsc.practiceProblem.services.practiceProblem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.services.problem.SubmitService;
import hanu.gdsc.share.domains.DateTime;

@Service
public class SubmitPracticeProblemServiceImpl implements SubmitPracticeProblemService{
    @Autowired
    private SubmitService submitService;


    @Override
    public SubmitService.Output submit(Input input) {
        return submitService.submit(input.inputService);
    }
}
