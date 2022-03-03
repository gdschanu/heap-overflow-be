package hanu.gdsc.coreProblem.repositories;

import java.util.UUID;

import hanu.gdsc.coreProblem.domains.TimeLimit;

public interface TimeLimitRepository {
    public TimeLimit getById(UUID id);
}
