package hanu.gdsc.contest_contest.services.contest;

import hanu.gdsc.contest_contest.domains.Contest;
import hanu.gdsc.contest_contest.repositories.contest.ContestRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.NotFoundError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchContestServiceImpl implements SearchContestService {
    private final ContestRepository contestRepository;

    private OutputContest toOutput(Contest contest) {
        return OutputContest.builder()
                .id(contest.getId())
                .name(contest.getName())
                .description(contest.getDescription())
                .startAt(contest.getStartAt())
                .endAt(contest.getEndAt())
                .createdBy(contest.getCreatedBy())
                .problems(contest.getProblems().stream().map(x -> OutputProblem.builder()
                        .ordinal(x.getOrdinal())
                        .coreProblemId(x.getCoreProblemId())
                        .score(x.getScore())
                        .build()).collect(Collectors.toList()))
                .version(contest.getVersion())
                .build();
    }

    @Override
    public OutputContest getById(Id contestId) {
        Contest contest = contestRepository.getById(contestId);
        if (contest == null) {
            throw new NotFoundError("Contest doesn't exist");
        }
        return toOutput(contest);
    }

    @Override
    public List<OutputContest> get(int page, int perPage) {
        List<Contest> contests = contestRepository.get(page, perPage);
        return contests.stream().map(x -> toOutput(x))
                .collect(Collectors.toList());
    }
}
