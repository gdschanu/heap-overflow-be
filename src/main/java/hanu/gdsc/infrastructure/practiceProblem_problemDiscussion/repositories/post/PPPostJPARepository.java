package hanu.gdsc.infrastructure.practiceProblem_problemDiscussion.repositories.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PPPostJPARepository extends JpaRepository<PPPostEntity, String> {
    public Page<PPPostEntity> findByProblemId(String problemId, Pageable pageable);

    public List<PPPostEntity> findByProblemId(String problemId);

    public long countByProblemId(String problemId);

    public void deleteAllByProblemId(String problemId);
}
