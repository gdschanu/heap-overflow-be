package hanu.gdsc.domain.contest.services.contest;

import hanu.gdsc.domain.contest.config.ContestServiceName;
import hanu.gdsc.domain.contest.exception.ContestEndedException;
import hanu.gdsc.domain.contest.exception.ContestNotStartedException;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.ContestProblem;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.core_problem.exceptions.NoTestCasesWereDefined;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.services.submit.SubmitService;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubmitContestProblemService {
    private final ContestRepository contestRepository;
    private final SubmitService submitCoreProblemService;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public int contestProblemOrdinal;
        public Id contestId;
        public Id coderId;
        public String code;
        public ProgrammingLanguage programmingLanguage;
    }

    public SubmitService.Output execute(Input input) throws NotFoundException,
            InvalidInputException, ContestEndedException, ContestNotStartedException, NoTestCasesWereDefined {
        final Contest contest = contestRepository.getById(input.contestId);
        if (contest == null) {
            throw new NotFoundException("Unknown contest.");
        }
        if (contest.ended()) {
            throw new ContestEndedException();
        }
        if (!contest.started()) {
            throw new ContestNotStartedException();
        }
        final ContestProblem contestProblem = contest.getProblem(input.contestProblemOrdinal);
        if (contestProblem == null) {
            throw new NotFoundException("Unknown contest problem ordinal");
        }
        return submitCoreProblemService.submit(new SubmitService.Input(
                input.coderId,
                contestProblem.getCoreProblemId(),
                ContestServiceName.serviceName,
                input.code,
                input.programmingLanguage
        ));
    }
}
