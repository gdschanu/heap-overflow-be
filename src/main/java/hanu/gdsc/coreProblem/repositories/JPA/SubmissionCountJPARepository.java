package hanu.gdsc.coreProblem.repositories.JPA;

import hanu.gdsc.coreProblem.repositories.entities.SubmissionCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubmissionCountJPARepository extends JpaRepository<SubmissionCountEntity, String> {
    @Query(value = "SELECT * FROM core_problem_submission_count sc WHERE sc.problem_id = :problemId", nativeQuery = true)
    public SubmissionCountEntity getById(String problemId);

    public List<SubmissionCountEntity> getByProblemId(String problemId);

    @Modifying
    @Query(value = " UPDATE core_problem_submission_count set " +
            " acs_count= :ACsCount, " +
            " submissions_count= :submissionsCount, " +
            " version= ( :version + 1) " +
            " WHERE problem_id= :problemId AND version= :version ", nativeQuery = true)
    void update(@Param("ACsCount") Integer ACsCount,
                @Param("submissionsCount") Integer submissionsCount,
                @Param("problemId") String problemId,
                @Param("version") Long version);

}
