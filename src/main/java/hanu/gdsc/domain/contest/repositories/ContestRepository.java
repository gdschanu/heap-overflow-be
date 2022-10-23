package hanu.gdsc.domain.contest.repositories;

import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface ContestRepository {
    public void create(Contest contest);

    public void update(Contest contest);

    public Contest getById(Id id);

    public List<Contest> get(int page, int perPage);

    public long count();
}
