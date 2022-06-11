package hanu.gdsc.contest.repositories.contest;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.share.domains.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ContestRepositoryImpl implements ContestRepository {
    @Autowired
    private ContestJPARepository contestJPARepository;

    @Override
    public void create(Contest contest) {
        ContestEntity e = ContestEntity.fromDomain(contest);
        contestJPARepository.save(e);
    }

    @Override
    public void update(Contest contest) {
        contestJPARepository.save(ContestEntity.fromDomain(contest));
    }

    @Override
    public Contest getById(Id id) {
        Optional<ContestEntity> entity = contestJPARepository.findById(id.toString());
        if (entity.isEmpty()) {
            return null;
        }
        return entity.get().toDomain();
    }

    @Override
    public List<Contest> get(int page, int perPage) {
        Page<ContestEntity> entities = contestJPARepository
                .findAll(Pageable.ofSize(perPage).withPage(page));
        return entities.getContent()
                .stream()
                .map(x -> x.toDomain())
                .collect(Collectors.toList());
    }
}
