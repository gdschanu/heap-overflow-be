package hanu.gdsc.contest.repositories;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.share.domains.Id;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContestRepositoryImpl implements ContestRepository {
    @Override
    public void create(Contest contest) {

    }

    @Override
    public void update(Contest contest) {

    }

    @Override
    public Contest getById(Id id) {
        return null;
    }

    @Override
    public List<Contest> search(int skip, int limit) {
        return null;
    }
}
