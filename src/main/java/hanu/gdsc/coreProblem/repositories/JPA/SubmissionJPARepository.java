package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hanu.gdsc.coreProblem.repositories.entities.SubmissionEntity;


public interface SubmissionJPARepository extends JpaRepository<SubmissionEntity, String> {
    @Query(value="SELECT * FROM core_problem_submission s WHERE (:problemId IS NULL OR s.problemId = :problemId)" +
        " AND (:coderId IS NULL OR s.coderId = :coderId)" +
        " AND s.serviceToCreate = :serviceToCreate",
        countQuery="SELECT count(*) FROM core_problem_submission s WHERE (:problemId IS NULL OR s.problemId = :problemId)" +
        " AND (:coderId IS NULL OR s.coderId = :coderId)" +
        " AND s.serviceToCreate = :serviceToCreate",
        nativeQuery = true)
    public Page<SubmissionEntity> get(@Param("problemId") String problemId,
                                      @Param("coderId") String coderId,
                                      @Param("serviceToCreate") String serviceToCreate,
                                      Pageable pageable);

    public SubmissionEntity getByIdAndServiceToCreate(String id, String serviceToCreate);                                  
}
