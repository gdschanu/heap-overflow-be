package hanu.gdsc.coder.services;

import hanu.gdsc.coder.domains.Coder;
import hanu.gdsc.coder.repositories.CoderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CoderRankingServiceImpl implements CoderRankingService {

    @Autowired
    public CoderRepository coderRepository;

    @Override
    public List<Coder> getAllCoderRankingSorted() {
        List<Coder> rankingCoders = coderRepository.getAll();
        Collections.sort(rankingCoders, new Comparator<Coder>() {
            @Override
            public int compare(Coder coder1, Coder coder2) {
                return coder1.getRank() - coder2.getRank() > 0 ? 1 : -1;
            }
        });
        return rankingCoders;
    }
}
