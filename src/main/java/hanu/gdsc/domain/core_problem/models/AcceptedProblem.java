package hanu.gdsc.domain.core_problem.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;

public class AcceptedProblem {
    private Id coderId;
    private Id problemId;
    private String serviceToCreate;

    public AcceptedProblem(Id coderId, Id problemId, String serviceToCreate) throws InvalidInputException {
        if (coderId == null) {
            throw new InvalidInputException("coderId must not be null.");
        }
        this.coderId = coderId;
        if (problemId == null) {
            throw new InvalidInputException("problemId must not be null.");
        }
        this.problemId = problemId;
        if (serviceToCreate == null) {
            throw new InvalidInputException("serviceToCreate must not be null.");
        }
        this.serviceToCreate = serviceToCreate;
    }

    public Id getCoderId() {
        return coderId;
    }

    public Id getProblemId() {
        return problemId;
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }
}
