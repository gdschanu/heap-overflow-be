package hanu.gdsc.core_problem.services.problem;

import hanu.gdsc.core_problem.domains.KB;
import hanu.gdsc.core_problem.domains.Millisecond;
import hanu.gdsc.core_problem.domains.Problem;
import hanu.gdsc.core_problem.domains.ProgrammingLanguage;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


public interface SearchProblemService {
    public Problem getById(Id id, String serviceToCreate);

    public List<Problem> getByIds(List<Id> ids, String serviceToCreate);
}
