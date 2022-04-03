package hanu.gdsc.practiceProblem.services.coreProblem;

import hanu.gdsc.coreProblem.domains.Submission;
import hanu.gdsc.coreProblem.services.submission.SearchSubmissionService;
import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SearchCoreProblemSubmissionServiceImpl implements SearchCoreProblemSubmissionService {
    private final SearchSubmissionService searchSubmissionService;

    @Override
    public List<Submission> get(int page, int perPage, Id problemId, Id coderId) {
        return null;
    }

    @Override
    public Submission getById(Id id) {
        return null;
    }
}
