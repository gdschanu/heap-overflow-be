package hanu.gdsc.domain.contest.services.submissionEvent;

import hanu.gdsc.domain.contest.config.ContestServiceName;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.ContestProblem;
import hanu.gdsc.domain.contest.models.Participant;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.contest.repositories.ParticipantRepository;
import hanu.gdsc.domain.core_problem.models.Status;
import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import hanu.gdsc.domain.core_problem.repositories.SubmissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProcessContestSubmissionEventService {
    private ContestRepository contestRepository;
    private ParticipantRepository participantRepository;
    private SubmissionRepository submissionRepository;

    public void process(SubmissionEvent submissionEvent, Runnable ack) {
        if (!submissionEvent.getServiceToCreate().equals(ContestServiceName.serviceName)) {
            ack.run();
            return;
        }
        log.info("Receive contest submission event: " + submissionEvent);
        final Contest contest = contestRepository.getContestContainsCoreProblemId(
                submissionEvent.getProblemId()
        );
        final Participant participant = participantRepository.getByCoderIdAndContestId(
                submissionEvent.getCoderId(),
                contest.getId()
        );
        final long notACSubmissionsBeforeCount = submissionRepository.countNotACSubmissionsBefore(
                submissionEvent.getCoderId(),
                submissionEvent.getProblemId(),
                ContestServiceName.serviceName,
                submissionEvent.getSubmittedAt()
        );
        final ContestProblem contestProblem = contest.getProblem(submissionEvent.getProblemId());
        final double score = contest.calculateScoreForSubmission(
                contestProblem.getOrdinal(),
                submissionEvent.getSubmittedAt(),
                notACSubmissionsBeforeCount,
                submissionEvent.getStatus(),
                submissionEvent.getPassedTestCasesCount(),
                submissionEvent.getTotalTestCasesCount()
        );
        participant.updateProblemScore(
                score,
                contestProblem.getOrdinal(),
                submissionEvent.getStatus().equals(Status.AC)
        );
        contestRepository.save(contest);
        participantRepository.save(participant);
        ack.run();
    }
}
