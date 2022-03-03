package hanu.gdsc.coreProblem.repositories;

import java.util.UUID;

import hanu.gdsc.coreProblem.domains.MemoryLimit;

public interface MemoryLimitRepository {
    public MemoryLimit getById(UUID id);
}
