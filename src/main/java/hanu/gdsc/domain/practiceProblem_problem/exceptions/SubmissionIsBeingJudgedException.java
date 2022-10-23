package hanu.gdsc.domain.practiceProblem_problem.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class SubmissionIsBeingJudgedException extends BusinessLogicException {
    public SubmissionIsBeingJudgedException() {
        super("Submission is being judged, please wait.", "JUDGING_SUBMISSION");
    }
}
