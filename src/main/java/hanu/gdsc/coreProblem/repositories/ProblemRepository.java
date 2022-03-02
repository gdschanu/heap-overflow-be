package hanu.gdsc.coreProblem.repositories;

import java.util.UUID;

import hanu.gdsc.coreProblem.domains.Problem;

public interface ProblemRepository {
    public Problem getById(UUID id);
    public void save();
    public void deleteById(UUID id);
}
