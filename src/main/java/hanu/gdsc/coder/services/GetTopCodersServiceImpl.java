package hanu.gdsc.coder.services;

import hanu.gdsc.coder.domains.Coder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetTopCodersServiceImpl implements GetTopCodersService {

    @Autowired
    private CoderRankingService coderRankingService;

    @Override
    public List<Coder> getTopCoders(int num) {
        List<Coder> rankingCoders = coderRankingService.getAllCoderRankingSorted();
        List<Coder> topCoders = new ArrayList<>();
        for(int i = 0; i < num; i++) {
            topCoders.add(rankingCoders.get(i));
        }
        return topCoders;
    }
}
