package hanu.gdsc.coreProblem.repositories.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hanu.gdsc.coreProblem.repositories.entities.EventEntity;

public interface EventJPARepository extends JpaRepository<EventEntity, Long> {
    @Query(value = "SELECT * FROM core_problem_event LIMIT 1", nativeQuery = true)
    public EventEntity getEventEntity();
}
