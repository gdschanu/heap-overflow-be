package hanu.gdsc.coreProblem.repositories;

import hanu.gdsc.coreProblem.domains.SubmissionEvent;
import hanu.gdsc.share.error.BusinessLogicError;
import org.springframework.stereotype.Repository;

@Repository
public class SubmissionEventRepositoryImpl implements SubmissionEventRepository {
    @Override
    public void save(SubmissionEvent event) {
        throw new Error("Unimplemented");
    }
}
