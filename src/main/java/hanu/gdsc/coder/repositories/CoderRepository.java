package hanu.gdsc.coder.repositories;

import hanu.gdsc.coder.domains.Coder;
import hanu.gdsc.share.domains.Id;

import java.util.List;

public interface CoderRepository {
    public void create(Coder coder);
    public List<Coder> get(int page, int perPage);
    public Coder getById(Id id);
}
