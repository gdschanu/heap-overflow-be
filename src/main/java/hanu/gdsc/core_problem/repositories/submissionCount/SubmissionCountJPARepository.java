package hanu.gdsc.core_problem.repositories.submissionCount;

import hanu.gdsc.core_problem.domains.SubmissionCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubmissionCountJPARepository extends JpaRepository<SubmissionCountEntity, String> {
    public SubmissionCountEntity findByProblemIdAndServiceToCreate(String problemId, String serviceToCreate);

    @Modifying
    @Transactional
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

    public List<SubmissionCountEntity> findByProblemIdInAndServiceToCreate(List<String> problemIds, String serviceToCreate);
}
