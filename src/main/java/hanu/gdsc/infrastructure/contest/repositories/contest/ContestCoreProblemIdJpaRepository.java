package hanu.gdsc.infrastructure.contest.repositories.contest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestCoreProblemIdJpaRepository extends JpaRepository<ContestCoreProblemIdEntity, String> {
    public ContestCoreProblemIdEntity findByCoreProblemId(String coreProblemId);

    public List<ContestCoreProblemIdEntity> findByCoreProblemIdIn(List<String> coreProblemIds);
}
