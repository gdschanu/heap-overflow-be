package hanu.gdsc.coreProblem.services.problem;

import java.util.List;
import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.share.domains.Id;


public interface SearchProblemService {
    public Problem getById(Id id);
    
    public long count();

    public List<Problem> getByIds(List<Id> ids);
}
