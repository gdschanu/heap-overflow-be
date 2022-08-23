package hanu.gdsc.coder.services;

import hanu.gdsc.coder.domains.Coder;

import java.util.List;

public interface GetTopCodersService {
    public List<Coder> getTopCoders(int num);
}
