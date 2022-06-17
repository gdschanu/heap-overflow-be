package hanu.gdsc.core_problem.services.runningSubmission;

import hanu.gdsc.core_problem.config.RunningSubmissionConfig;
import hanu.gdsc.core_problem.domains.*;
import hanu.gdsc.core_problem.repositories.problem.ProblemRepository;
import hanu.gdsc.core_problem.repositories.runningSubmission.RunningSubmissionRepository;
import hanu.gdsc.core_problem.repositories.submission.SubmissionRepository;
import hanu.gdsc.core_problem.repositories.submissionEvent.SubmissionEventRepository;
import hanu.gdsc.core_problem.repositories.testCase.TestCaseRepository;
import hanu.gdsc.share.scheduling.Scheduler;
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
    private final Judger judger;
    private final ProblemRepository problemRepository;
    private final SubmissionRepository submissionRepository;
    private final SubmissionEventRepository submissionEventRepository;
    private final UpdateRunningSubmissionQueue updateRunningSubmissionQueue;

    public ProcessRunningSubmissionService(RunningSubmissionRepository runningSubmissionRepository,
                                           TestCaseRepository testCaseRepository,
                                           Judger judger,
                                           ProblemRepository problemRepository,
                                           SubmissionRepository submissionRepository,
                                           SubmissionEventRepository submissionEventRepository,
                                           UpdateRunningSubmissionQueue updateRunningSubmissionQueue) {
        this.runningSubmissionRepository = runningSubmissionRepository;
        this.testCaseRepository = testCaseRepository;
        this.judger = judger;
        this.problemRepository = problemRepository;
        this.submissionRepository = submissionRepository;
        this.submissionEventRepository = submissionEventRepository;
        this.updateRunningSubmissionQueue = updateRunningSubmissionQueue;

        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(RunningSubmissionConfig.MAX_THREAD);
        new Scheduler(RunningSubmissionConfig.RATE_MILLIS, new Scheduler.Runner() {
            @Override
            protected void run() throws Throwable {
                process();
            }
        }).start();
    }

    private boolean allThreadsAreActive() {
        return executor.getActiveCount() == RunningSubmissionConfig.MAX_THREAD;
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
        Millisecond totalRunTime = new Millisecond(0L);
        KB totalMemLimit = new KB(0);

        for (int i = runningSubmission.getJudgingTestCase() - 1; i < testCases.size(); i++) {
            runningSubmission.setJudgingTestCase(i + 1);
            runningSubmission.setTotalTestCases(testCases.size());
            updateRunningSubmissionQueue.add(runningSubmission);

            TestCase testCase = testCases.get(i);
            MemoryLimit memoryLimit = problem.getMemoryLimitByProgrammingLanguage(
                    runningSubmission.getProgrammingLanguage()
            );
            TimeLimit timeLimit = problem.getTimeLimitByProgrammingLanguage(
                    runningSubmission.getProgrammingLanguage()
            );
            Judger.Submission judgeSubmission = judger.createSubmission(
                    runningSubmission.getCode(),
                    testCase.getInput(),
                    runningSubmission.getProgrammingLanguage()
            );
            totalMemLimit = totalMemLimit.plus(judgeSubmission.memory());
            totalRunTime = totalRunTime.plus(judgeSubmission.runTime());
            Millisecond avgRunTime = totalRunTime.divide(i + 1);
            KB avgMemLimit = totalMemLimit.divide(i + 1);
            Submission submission = null;
            if (judgeSubmission.compilationError()) {
                submission = Submission.createWithId(
                        runningSubmission.getId(),
                        runningSubmission.getProblemId(),
                        runningSubmission.getProgrammingLanguage(),
                        avgRunTime,
                        avgMemLimit,
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
                        avgRunTime,
                        avgMemLimit,
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
                        avgRunTime,
                        avgMemLimit,
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
                        avgRunTime,
                        avgMemLimit,
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
            } else if (!judgeSubmission.output().equals(testCase.getExpectedOutput())) {
                submission = Submission.createWithId(
                        runningSubmission.getId(),
                        runningSubmission.getProblemId(),
                        runningSubmission.getProgrammingLanguage(),
                        avgRunTime,
                        avgMemLimit,
                        runningSubmission.getCode(),
                        Status.WA,
                        FailedTestCaseDetail.fromTestCase(
                                judgeSubmission.output().differentLine(testCase.getExpectedOutput()),
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
                        avgRunTime,
                        avgMemLimit,
                        runningSubmission.getCode(),
                        Status.AC,
                        null,
                        runningSubmission.getServiceToCreate(),
                        runningSubmission.getCoderId(),
                        judgeSubmission.stdMessage()
                );
            }

            if (i == testCases.size() - 1 || !submission.getStatus().equals(Status.AC)) {
                saveSubmission(submission, runningSubmission);
            }
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    private void saveSubmission(Submission submission, RunningSubmission runningSubmission) {
        submissionRepository.save(submission);
        submissionEventRepository.save(
                SubmissionEvent.create(
                        runningSubmission.getProblemId(),
                        submission.getStatus()
                )
        );
        runningSubmissionRepository.delete(runningSubmission.getId());
    }
}
