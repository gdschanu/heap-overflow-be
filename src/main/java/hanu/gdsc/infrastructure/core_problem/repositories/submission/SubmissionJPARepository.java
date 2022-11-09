package hanu.gdsc.infrastructure.core_problem.repositories.submission;

import hanu.gdsc.domain.core_problem.models.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SubmissionJPARepository extends JpaRepository<SubmissionEntity, String> {
    public SubmissionEntity getByIdAndServiceToCreate(String id, String serviceToCreate);

    public Page<SubmissionEntity> findByServiceToCreate(String serviceToCreate,
                                                        Pageable pageable);

    public Page<SubmissionEntity> findByProblemIdAndServiceToCreate(String problemId,
                                                                    String serviceToCreate,
                                                                    Pageable pageable);

    public Page<SubmissionEntity> findByCoderIdAndServiceToCreate(String coderId,
                                                                  String serviceToCreate,
                                                                  Pageable pageable);

    public Page<SubmissionEntity> findByProblemIdAndCoderIdAndServiceToCreate(String problemId,
                                                                              String coderId,
                                                                              String serviceToCreate,
                                                                              Pageable pageable);

    public void deleteAllByProblemId(String problemId);

    @Query("SELECT s FROM SubmissionEntity s WHERE s.problemId = : problemId AND s.coderId = : coderId AND s.status = 'AC'")
    public Optional<SubmissionEntity> findByProblemIdAndCoderId(String problemId, String coderId);
}
