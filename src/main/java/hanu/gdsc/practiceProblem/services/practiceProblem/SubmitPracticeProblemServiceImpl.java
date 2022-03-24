package hanu.gdsc.practiceProblem.services.practiceProblem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.services.problem.SubmitService;

@Service
public class SubmitPracticeProblemServiceImpl implements SubmitPracticeProblemService{
    @Autowired
    private SubmitService submitService;


    @Override
    public SubmitService.Output submit(SubmitService.Input input) {
        input.serviceName = "PracticeProblemService";
        return submitService.submit(input);
    }
}
