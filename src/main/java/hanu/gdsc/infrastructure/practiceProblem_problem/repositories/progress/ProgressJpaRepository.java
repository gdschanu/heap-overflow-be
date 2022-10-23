package hanu.gdsc.infrastructure.practiceProblem_problem.repositories.progress;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressJpaRepository extends JpaRepository<ProgressEntity, String> {
    public ProgressEntity findByCoderId(String coderId);
}
