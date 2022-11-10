package hanu.gdsc.infrastructure.contest.repositories.contest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestCoreProblemIdJpaRepository extends JpaRepository<ContestCoreProblemIdEntity, String> {
    public ContestCoreProblemIdEntity findByCoreProblemId(String coreProblemId);
}
