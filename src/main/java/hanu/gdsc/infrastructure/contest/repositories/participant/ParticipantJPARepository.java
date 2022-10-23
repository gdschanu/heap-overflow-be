package hanu.gdsc.infrastructure.contest.repositories.participant;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ParticipantJPARepository extends PagingAndSortingRepository<ParticipantEntity, String> {
    public List<ParticipantEntity> findByContestId(String contestId, Pageable pageable);

    List<ParticipantEntity> findByCoderId(String coderId);

    @Query(value = "SELECT COUNT(u) FROM contest_participant u WHERE u.contest_id=:contestId", nativeQuery = true)
    public long counByContestId(String contestId);
}
