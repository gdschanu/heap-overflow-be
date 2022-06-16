package hanu.gdsc.contestSubdomain.contestContext.repositories.contest;

import hanu.gdsc.contestSubdomain.contestContext.domains.Contest;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface ContestRepository {
    public void create(Contest contest);

    public void update(Contest contest);

    public Contest getById(Id id);

    public List<Contest> get(int page, int perPage);
}
