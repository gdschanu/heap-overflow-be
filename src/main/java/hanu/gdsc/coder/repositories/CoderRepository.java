package hanu.gdsc.coder.repositories;

import hanu.gdsc.coder.domains.Coder;

import java.util.List;

public interface CoderRepository {
    public void create(Coder coder);
    public List<Coder> get(int page, int perPage);
}
