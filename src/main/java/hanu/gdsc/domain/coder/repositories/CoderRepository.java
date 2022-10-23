package hanu.gdsc.domain.coder.repositories;

import hanu.gdsc.domain.coder.models.Coder;
import hanu.gdsc.domain.share.models.Id;

import java.util.List;

public interface CoderRepository {
    public void save(Coder coder);
    public List<Coder> get(int page, int perPage);
    public Coder getById(Id id);
}
