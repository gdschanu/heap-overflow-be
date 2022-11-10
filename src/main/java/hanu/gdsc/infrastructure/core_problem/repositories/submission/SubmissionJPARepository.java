package hanu.gdsc.infrastructure.core_problem.repositories.submission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubmissionJPARepository extends JpaRepository<SubmissionEntity, String> {
    public SubmissionEntity getByIdAndServiceToCreate(String id, String serviceToCreate);

    public Page<SubmissionEntity> findByServiceToCreate(String serviceToCreate,
                                                        Pageable pageable);

    public Page<SubmissionEntity> findByProblemIdAndServiceToCreate(String problemId,
                                                                    String serviceToCreate,
                                                                    Pageable pageable);

    public Page<SubmissionEntity> findByProblemIdInAndServiceToCreate(List<String> problemIds,
                                                                    String serviceToCreate,
                                                                    Pageable pageable);

    public Page<SubmissionEntity> findByCoderIdAndServiceToCreate(String coderId,
                                                                  String serviceToCreate,
                                                                  Pageable pageable);

    public Page<SubmissionEntity> findByProblemIdAndCoderIdAndServiceToCreate(String problemId,
                                                                              String coderId,
                                                                              String serviceToCreate,
                                                                              Pageable pageable);

    public Page<SubmissionEntity> findByProblemIdInAndCoderIdAndServiceToCreate(List<String> problemId,
                                                                              String coderId,
                                                                              String serviceToCreate,
                                                                              Pageable pageable);

    public void deleteAllByProblemId(String problemId);

    @Query(value = "select s.problemId from SubmissionEntity s where s.status = 'CE' and " +
            "s.coderId = :coderId and s.serviceToCreate = :serviceToCreate group by s.problemId")
    public List<String> getAllProblemIdACByCoderIdAndServiceToCreate(String coderId, String serviceToCreate);

    public long countByCoderIdAndProblemIdAndServiceToCreateAndSubmittedAtMillisLessThanAndStatusNot(
            String coderId,
            String problemId,
            String serviceToCreate,
            long submittedAt,
            String status
    );
}
