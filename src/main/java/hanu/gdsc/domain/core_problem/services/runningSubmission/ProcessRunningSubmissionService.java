package hanu.gdsc.domain.core_problem.services.runningSubmission;

import hanu.gdsc.domain.core_problem.config.RunningSubmissionConfig;
import hanu.gdsc.domain.core_problem.models.*;
import hanu.gdsc.domain.core_problem.repositories.*;
import hanu.gdsc.domain.core_problem.vm.VirtualMachine;
import hanu.gdsc.domain.share.scheduling.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class ProcessRunningSubmissionService {
    private ThreadPoolExecutor executor;
    private final RunningSubmissionRepository runningSubmissionRepository;
    private final TestCaseRepository testCaseRepository;
    private final VirtualMachine virtualMachine;
    private final ProblemRepository problemRepository;
    private final SubmissionRepository submissionRepository;
    private final SubmissionEventRepository submissionEventRepository;
    private final RunningSubmissionConfig runningSubmissionConfig;

    public ProcessRunningSubmissionService(RunningSubmissionRepository runningSubmissionRepository,
                                           TestCaseRepository testCaseRepository,
                                           VirtualMachine virtualMachine,
                                           ProblemRepository problemRepository,
                                           SubmissionRepository submissionRepository,
                                           SubmissionEventRepository submissionEventRepository,
                                           RunningSubmissionConfig runningSubmissionConfig) {
        this.runningSubmissionRepository = runningSubmissionRepository;
        this.testCaseRepository = testCaseRepository;
        this.virtualMachine = virtualMachine;
        this.problemRepository = problemRepository;
        this.submissionRepository = submissionRepository;
        this.submissionEventRepository = submissionEventRepository;
        this.runningSubmissionConfig = runningSubmissionConfig;

        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(runningSubmissionConfig.getMaxProcessingThread());
        new Scheduler(runningSubmissionConfig.getScanRateMillis(), new Scheduler.Runner() {
            @Override
            protected void run() throws Throwable {
                process();
            }
        }).start();
    }

    private boolean allThreadsAreActive() {
        return executor.getActiveCount() == runningSubmissionConfig.getMaxProcessingThread();
    }

    private synchronized void process() {
        if (!allThreadsAreActive()) {
            RunningSubmission runningSubmission = runningSubmissionRepository.claim();
            if (runningSubmission != null) {
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            processSubmission(runningSubmission);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    private void processSubmission(RunningSubmission runningSubmission) throws IOException, InterruptedException {
        List<TestCase> testCases = TestCase.sortByOrdinal(
                testCaseRepository.getByProblemId(
                        runningSubmission.getProblemId(),
                        runningSubmission.getServiceToCreate()
                )
        );
        Problem problem = problemRepository.getById(
                runningSubmission.getProblemId(),
                runningSubmission.getServiceToCreate()
        );
        Millisecond maxRunTime = new Millisecond(0L);
        KB maxMem = new KB(0);
        int start = Math.max(
                0,
                runningSubmission.getJudgingTestCase() - 1
        );
        for (int i = start; i < testCases.size(); i++) {
            runningSubmission.setJudgingTestCase(i + 1);
            runningSubmission.setTotalTestCases(testCases.size());
            runningSubmissionRepository.updateClaimed(runningSubmission);

            TestCase testCase = testCases.get(i);
            MemoryLimit memoryLimit = problem.getMemoryLimitByProgrammingLanguage(
                    runningSubmission.getProgrammingLanguage()
            );
            TimeLimit timeLimit = problem.getTimeLimitByProgrammingLanguage(
                    runningSubmission.getProgrammingLanguage()
            );
            VirtualMachine.Submission judgeSubmission = virtualMachine.createSubmission(
                    runningSubmission.getCode(),
                    testCase.getInput(),
                    runningSubmission.getProgrammingLanguage()
            );
            maxMem = KB.max(maxMem, judgeSubmission.memory());
            maxRunTime = Millisecond.max(maxRunTime, judgeSubmission.runTime());
            Submission submission = null;
            if (memoryLimit == null) {
                submission = Submission.createWithId(
                        runningSubmission.getId(),
                        runningSubmission.getProblemId(),
                        runningSubmission.getProgrammingLanguage(),
                        maxRunTime,
                        maxMem,
                        runningSubmission.getCode(),
                        Status.STDE,
                        FailedTestCaseDetail.fromTestCase(
                                0,
                                "",
                                testCase
                        ),
                        runningSubmission.getServiceToCreate(),
                        runningSubmission.getCoderId(),
                        "No memory limit."
                );
            } else if (timeLimit == null) {
                submission = Submission.createWithId(
                        runningSubmission.getId(),
                        runningSubmission.getProblemId(),
                        runningSubmission.getProgrammingLanguage(),
                        maxRunTime,
                        maxMem,
                        runningSubmission.getCode(),
                        Status.STDE,
                        FailedTestCaseDetail.fromTestCase(
                                0,
                                "",
                                testCase
                        ),
                        runningSubmission.getServiceToCreate(),
                        runningSubmission.getCoderId(),
                        "No time limit."
                );
            } else if (judgeSubmission.compilationError()) {
                submission = Submission.createWithId(
                        runningSubmission.getId(),
                        runningSubmission.getProblemId(),
                        runningSubmission.getProgrammingLanguage(),
                        maxRunTime,
                        maxMem,
                        runningSubmission.getCode(),
                        Status.CE,
                        FailedTestCaseDetail.fromTestCase(
                                0,
                                "",
                                testCase
                        ),
                        runningSubmission.getServiceToCreate(),
                        runningSubmission.getCoderId(),
                        judgeSubmission.compilationMessage()
                );
            } else if (judgeSubmission.stdError()) {
                submission = Submission.createWithId(
                        runningSubmission.getId(),
                        runningSubmission.getProblemId(),
                        runningSubmission.getProgrammingLanguage(),
                        maxRunTime,
                        maxMem,
                        runningSubmission.getCode(),
                        Status.STDE,
                        FailedTestCaseDetail.fromTestCase(
                                0,
                                "",
                                testCase
                        ),
                        runningSubmission.getServiceToCreate(),
                        runningSubmission.getCoderId(),
                        judgeSubmission.stdMessage()
                );
            } else if (judgeSubmission.memory().greaterThan(memoryLimit.getMemoryLimit())) {
                submission = Submission.createWithId(
                        runningSubmission.getId(),
                        runningSubmission.getProblemId(),
                        runningSubmission.getProgrammingLanguage(),
                        maxRunTime,
                        maxMem,
                        runningSubmission.getCode(),
                        Status.MLE,
                        FailedTestCaseDetail.fromTestCase(
                                0,
                                judgeSubmission.output().toString(),
                                testCase
                        ),
                        runningSubmission.getServiceToCreate(),
                        runningSubmission.getCoderId(),
                        judgeSubmission.stdMessage()
                );
            } else if (judgeSubmission.runTime().greaterThan(timeLimit.getTimeLimit())) {
                submission = Submission.createWithId(
                        runningSubmission.getId(),
                        runningSubmission.getProblemId(),
                        runningSubmission.getProgrammingLanguage(),
                        maxRunTime,
                        maxMem,
                        runningSubmission.getCode(),
                        Status.TLE,
                        FailedTestCaseDetail.fromTestCase(
                                0,
                                judgeSubmission.output().toString(),
                                testCase
                        ),
                        runningSubmission.getServiceToCreate(),
                        runningSubmission.getCoderId(),
                        judgeSubmission.stdMessage()
                );
            } else {
                OutputComparator.CompareResult compareResult = OutputComparator.compare(
                        testCase.getExpectedOutput(),
                        judgeSubmission.output()
                );
                if (!compareResult.equal) {
                    submission = Submission.createWithId(
                            runningSubmission.getId(),
                            runningSubmission.getProblemId(),
                            runningSubmission.getProgrammingLanguage(),
                            maxRunTime,
                            maxMem,
                            runningSubmission.getCode(),
                            Status.WA,
                            FailedTestCaseDetail.fromTestCase(
                                    compareResult.differentLine,
                                    judgeSubmission.output().toString(),
                                    testCase
                            ),
                            runningSubmission.getServiceToCreate(),
                            runningSubmission.getCoderId(),
                            judgeSubmission.stdMessage()
                    );
                } else {
                    submission = Submission.createWithId(
                            runningSubmission.getId(),
                            runningSubmission.getProblemId(),
                            runningSubmission.getProgrammingLanguage(),
                            maxRunTime,
                            maxMem,
                            runningSubmission.getCode(),
                            Status.AC,
                            null,
                            runningSubmission.getServiceToCreate(),
                            runningSubmission.getCoderId(),
                            judgeSubmission.stdMessage()
                    );
                }
            }

            if (i == testCases.size() - 1 || !submission.getStatus().equals(Status.AC)) {
                saveSubmission(submission, runningSubmission);
                break;
            }
        }
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    private void saveSubmission(Submission submission, RunningSubmission runningSubmission) {
        runningSubmissionRepository.delete(runningSubmission.getId());
        submissionRepository.save(submission);
        submissionEventRepository.save(
                SubmissionEvent.create(
                        runningSubmission.getProblemId(),
                        submission.getStatus(),
                        submission.getCoderId()
                )
        );
    }
}
