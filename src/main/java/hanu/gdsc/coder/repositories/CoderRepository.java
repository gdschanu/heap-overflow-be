package hanu.gdsc.coder.repositories;

import hanu.gdsc.coder.domains.Coder;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;

import java.util.List;

public interface CoderRepository {
    public void save(Coder coder);
    public List<Coder> get(int page, int perPage);
    public Coder getById(Id id) throws InvalidInputException;
}
