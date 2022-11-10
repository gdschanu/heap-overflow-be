package hanu.gdsc.domain.core_problem.exceptions;

import hanu.gdsc.domain.share.exceptions.BusinessLogicException;

public class NoTestCasesWereDefined extends BusinessLogicException {
    public NoTestCasesWereDefined() {
        super("No test cases were defined for this problem, please try again later", "NO_TEST_CASE_WERE_DEFINED");
    }
}
