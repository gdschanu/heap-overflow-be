package hanu.gdsc.infrastructure.contest.repositories.contest;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ContestRepositoryImpl implements ContestRepository {
    @Autowired
    private ContestJPARepository contestJPARepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ContestCoreProblemIdJpaRepository contestCoreProblemIdJpaRepository;

    @Override
    public void save(Contest contest) {
        ContestEntity e = ContestEntity.fromDomain(contest, objectMapper);
        List<ContestCoreProblemIdEntity> contestCoreProblemIdEntities = new ArrayList<>();
        contest.getProblems().forEach(prob -> {
            contestCoreProblemIdEntities.add(new ContestCoreProblemIdEntity(
                    contest.getId().toString(),
                    prob.getCoreProblemId().toString()
            ));
        });
        contestCoreProblemIdJpaRepository.saveAll(contestCoreProblemIdEntities);
        contestJPARepository.save(e);
    }

    @Override
    public Contest getById(Id id) {
        Optional<ContestEntity> entity = contestJPARepository.findById(id.toString());
        if (entity.isEmpty()) {
            return null;
        }
        return entity.get().toDomain(objectMapper);
    }

    @Override
    public List<Contest> get(int page, int perPage) {
        Pageable pageable = PageRequest.of(page, perPage, Sort.by("createdAtMillis").descending());
        Page<ContestEntity> entities = contestJPARepository
                .findAll(pageable);
        return entities.getContent()
                .stream()
                .map(x -> x.toDomain(objectMapper))
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return contestJPARepository.count();
    }

    @Override
    public Contest getContestContainsCoreProblemId(Id coreProblemId) {
        final ContestCoreProblemIdEntity e = contestCoreProblemIdJpaRepository
                .findByCoreProblemId(coreProblemId.toString());
        if (e == null)
            return null;
        try {
            return getById(new Id(e.getContestId()));
        } catch (InvalidInputException x) {
            // Cannot reach
            return null;
        }
    }
}
