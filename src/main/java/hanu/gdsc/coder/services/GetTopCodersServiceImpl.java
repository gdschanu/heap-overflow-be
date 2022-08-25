package hanu.gdsc.coder.services;

import hanu.gdsc.coder.domains.Coder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTopCodersServiceImpl implements GetTopCodersService {

//    @Autowired
//    private CodeRanking coderRankingService;

    @Override
    public List<Coder> getTopCoders(int num) {
//        List<Coder> rankingCoders = coderRankingService.get();
//        List<Coder> topCoders = new ArrayList<>();
//        for(int i = 0; i < num; i++) {
//            topCoders.add(rankingCoders.get(i));
//        }
//        return topCoders;
        return null;
    }
}
