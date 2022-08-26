package hanu.gdsc.practiceProblem_problemDiscussion.repositories.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PPPostJPARepository extends JpaRepository<PPPostEntity, String> {
    public Page<PPPostEntity> findByProblemIdAndCoderId(String problemId, String coderId, Pageable pageable);

    public Page<PPPostEntity> findByProblemId(String problemId, Pageable pageable);

    public Page<PPPostEntity> findByCoderId(String coderId, Pageable pageable);

}
