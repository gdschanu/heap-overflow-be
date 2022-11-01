package hanu.gdsc.domain.contest.services.submissionEvent;

import hanu.gdsc.domain.contest.config.ContestServiceName;
import hanu.gdsc.domain.contest.models.Contest;
import hanu.gdsc.domain.contest.models.Participant;
import hanu.gdsc.domain.contest.repositories.ContestRepository;
import hanu.gdsc.domain.contest.repositories.ParticipantRepository;
import hanu.gdsc.domain.core_problem.models.Status;
import hanu.gdsc.domain.core_problem.models.SubmissionEvent;
import hanu.gdsc.domain.core_problem.repositories.SubmissionRepository;

public class ProcessSubmissionEventService {
    private ContestRepository contestRepository;
    private ParticipantRepository participantRepository;
    private SubmissionRepository submissionRepository;

    public void process(SubmissionEvent submissionEvent, Runnable ack) {
        if (submissionEvent.getStatus().equals(Status.AC)) {
            final Contest contest = contestRepository.getContestContainsCoreProblemId(
                    submissionEvent.getProblemId()
            );
            final Participant participant = participantRepository.getByCoderIdAndContestId(
                    submissionEvent.getCoderId(),
                    contest.getId()
            );
            final int notACSubmissionsBeforeCount = submissionRepository.countNotACSubmissionsBefore(
                    submissionEvent.getCoderId(),
                    submissionEvent.getProblemId(),
                    ContestServiceName.serviceName,
                    submissionEvent.getSubmittedAt()
            );
            final double score = contest.calculateScoreForACSubmission(
                    contest.getProblem(submissionEvent.getProblemId()).getOrdinal(),
                    submissionEvent.getSubmittedAt(),
                    notACSubmissionsBeforeCount
            );

        }
        ack.run();
    }
}
