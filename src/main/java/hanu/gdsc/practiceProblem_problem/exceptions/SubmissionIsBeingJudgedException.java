package hanu.gdsc.practiceProblem_problem.exceptions;

import hanu.gdsc.share.exceptions.BusinessLogicException;

public class SubmissionIsBeingJudgedException extends BusinessLogicException {
    public SubmissionIsBeingJudgedException() {
        super("Submission is being judged, please wait.", "JUDGING_SUBMISSION");
    }
}
