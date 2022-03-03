package hanu.gdsc.coreProblem.repositories;

import java.util.UUID;

import hanu.gdsc.coreProblem.domains.TestCase;

public interface TestCaseRepository {
    public TestCase getById(UUID id);
}
