package hanu.gdsc.practiceProblem.repositories.JPA;

import hanu.gdsc.practiceProblem.repositories.entities.LikeCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeCountJPARepository extends JpaRepository<LikeCountEntity, String> {
    public List<LikeCountEntity> findByProblemIdIn(List<String> problemIds);

    public LikeCountEntity findByProblemId(String problemId);
}
