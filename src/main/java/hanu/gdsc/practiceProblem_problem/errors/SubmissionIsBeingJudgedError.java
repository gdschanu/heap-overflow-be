package hanu.gdsc.practiceProblem_problem.errors;

import hanu.gdsc.share.error.BusinessLogicError;

public class SubmissionIsBeingJudgedError extends BusinessLogicError {
    public SubmissionIsBeingJudgedError() {
        super("Submission is being judged, please wait.", "JUDGING_SUBMISSION");
    }
}
