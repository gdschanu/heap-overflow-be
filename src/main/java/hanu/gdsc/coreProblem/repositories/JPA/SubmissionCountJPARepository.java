package hanu.gdsc.coreProblem.repositories.JPA;

import hanu.gdsc.coreProblem.repositories.entities.SubmissionCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubmissionCountJPARepository extends JpaRepository<SubmissionCountEntity, String> {
    public SubmissionCountEntity findByProblemIdAndServiceToCreate(String problemId, String serviceToCreate);

    @Modifying
    @Query(value = " UPDATE core_problem_submission_count set " +
            " acs_count= :ACsCount, " +
            " submissions_count= :submissionsCount, " +
            " version= ( :version + 1) " +
            " WHERE problem_id= :problemId AND version= :version ", nativeQuery = true)
    void update(@Param("ACsCount") Long ACsCount,
                @Param("submissionsCount") Long submissionsCount,
                @Param("problemId") String problemId,
                @Param("version") Long version);

    public SubmissionCountEntity findByProblemId(String problemId);

}
