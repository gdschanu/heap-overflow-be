package hanu.gdsc.practiceProblem.repositories.dislikeCount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DislikeCountJPARepository extends JpaRepository<DislikeCountEntity, String> {
    public List<DislikeCountEntity> findByProblemIdIn(List<String> problemIds);

    public DislikeCountEntity findByProblemId(String problemId);
}
