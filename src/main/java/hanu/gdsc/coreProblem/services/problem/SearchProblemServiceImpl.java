package hanu.gdsc.coreProblem.services.problem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import hanu.gdsc.coreProblem.domains.Problem;

public class SearchProblemServiceImpl implements SearchProblemService{

    @Override
    public List<Problem> search(Pageable pageable) {

        return null;
    }
    
}
