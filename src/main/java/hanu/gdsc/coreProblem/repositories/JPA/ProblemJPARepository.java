package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coreProblem.repositories.entities.ProblemEntity;

import java.util.List;

public interface ProblemJPARepository extends JpaRepository<ProblemEntity, String> {
    public List<ProblemEntity> findByIdIn(List<String> ids);
}
