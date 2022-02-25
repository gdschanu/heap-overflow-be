package hanu.gdsc.problem.services.submission;

import hanu.gdsc.problem.domains.Submission;
import hanu.gdsc.share.domains.ID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetSubmissionServiceImpl implements GetSubmissionService {
    @Override
    public Submission getById(ID id) {
        return null;
    }
}
