package hanu.gdsc.coreProblem.services.problem;

import java.util.List;

import org.springframework.data.domain.Pageable;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


public interface SearchProblemService {
    public Problem getById(Id id);
    
    public long count();

    public List<Problem> getByIds(List<Id> ids);
}
