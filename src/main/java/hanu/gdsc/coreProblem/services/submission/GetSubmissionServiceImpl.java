package hanu.gdsc.coreProblem.services.submission;

import hanu.gdsc.coreProblem.domains.Submission;
import lombok.AllArgsConstructor;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetSubmissionServiceImpl implements GetSubmissionService {
    @Override
    public Submission getById(UUID id) {
        return null;
    }
}
