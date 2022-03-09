package hanu.gdsc.coreProblem.services.problem;

import java.util.List;

import hanu.gdsc.share.domains.Id;

public interface DeleteProblemService {

    public void deleteAllById(List<Id> ids);
}
