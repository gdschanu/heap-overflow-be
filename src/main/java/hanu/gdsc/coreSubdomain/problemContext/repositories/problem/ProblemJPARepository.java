package hanu.gdsc.coreSubdomain.problemContext.repositories.problem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemJPARepository extends JpaRepository<ProblemEntity, String> {
    public List<ProblemEntity> findByIdInAndServiceToCreate(List<String> ids, String serviceToCreate);

    public ProblemEntity findByIdAndServiceToCreate(String id, String serviceToCreate);
}
