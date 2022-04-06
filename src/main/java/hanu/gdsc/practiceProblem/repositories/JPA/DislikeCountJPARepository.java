package hanu.gdsc.practiceProblem.repositories.JPA;

import hanu.gdsc.coreProblem.repositories.entities.ProblemEntity;
import hanu.gdsc.practiceProblem.domains.DislikeCount;
import hanu.gdsc.practiceProblem.repositories.entities.DislikeCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DislikeCountJPARepository extends JpaRepository<DislikeCountEntity, String> {
    public List<DislikeCountEntity> findByProblemIdIn(List<String> problemIds);

    public DislikeCountEntity findByProblemId(String problemId);
}
