package hanu.gdsc.domain.core_problem.services.runningSubmission;

import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.models.RunningSubmission;
import hanu.gdsc.domain.share.models.DateTime;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

public interface SearchRunningSubmissionService {

    public List<RunningSubmission> getByProblemIdAndCoderId(Id problemId,
                                                            Id coderId,
                                                            int page,
                                                            int perPage,
                                                            String serviceToCreate);

    public List<RunningSubmission> getByProblemIdsAndCoderId(List<Id> problemIds,
                                                  Id coderId,
                                                  int page,
                                                  int perPage,
                                                  String serviceToCreate);

    public RunningSubmission getById(Id id, String serviceToCreate);
}
