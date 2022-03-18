package hanu.gdsc.contest.repositories.JPA;

import hanu.gdsc.contest.repositories.entities.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipantJPARepository extends JpaRepository<ParticipantEntity, String> {
}
