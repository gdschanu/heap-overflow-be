package hanu.gdsc.contest.repositories.JPA;

import hanu.gdsc.contest.repositories.entities.ContestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContestJPARepository extends JpaRepository<ContestEntity, UUID> {
}
