package hanu.gdsc.coreProblem.repositories.JPA;

import hanu.gdsc.coreProblem.repositories.entities.SubmissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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

    public Page<SubmissionEntity> findByCoderIdAndServiceToCreate(String coderId,
                                                                  String serviceToCreate,
                                                                  Pageable pageable);

    public Page<SubmissionEntity> findByProblemIdAndCoderIdAndServiceToCreate(String problemId,
                                                                              String coderId,
                                                                              String serviceToCreate,
                                                                              Pageable pageable);
}
