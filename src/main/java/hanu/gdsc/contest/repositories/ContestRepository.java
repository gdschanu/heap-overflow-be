package hanu.gdsc.contest.repositories;

import hanu.gdsc.contest.domains.Contest;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface ContestRepository {
    public void create(Contest contest);

    public void update(Contest contest);

    public Contest getById(Id id);

    public List<Contest> search(int skip, int limit);
}
