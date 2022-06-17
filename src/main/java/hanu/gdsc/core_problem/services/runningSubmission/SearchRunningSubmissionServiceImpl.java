package hanu.gdsc.core_problem.services.runningSubmission;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.core_problem.config.RunningSubmissionConfig;
import hanu.gdsc.core_problem.domains.RunningSubmission;
import hanu.gdsc.core_problem.repositories.runningSubmission.RunningSubmissionRepository;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.scheduling.Scheduler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchRunningSubmissionServiceImpl implements SearchRunningSubmissionService {
    private ServerSocket serverSocket;
    private final RunningSubmissionRepository runningSubmissionRepository;
    private final ObjectMapper objectMapper;

    public SearchRunningSubmissionServiceImpl(RunningSubmissionRepository runningSubmissionRepository,
                                              ObjectMapper objectMapper) throws IOException {
        this.runningSubmissionRepository = runningSubmissionRepository;
        this.objectMapper = objectMapper;
        serverSocket = new ServerSocket(RunningSubmissionConfig.PORT);
        new Scheduler(RunningSubmissionConfig.GET_NEW_SOCKET_RATE_MILLIS, new Scheduler.Runner() {
            @Override
            public void run() throws Exception {
                getNewSocketThread();
            }
        }).start();
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class SocketOutput {
        public Id coderId;
        public Id problemId;
        public DateTime submittedAt;

        public int judgingTestCase;
        public int totalTestCases;
    }

    private void getNewSocketThread() throws Exception {
        Socket socket = serverSocket.accept();
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        new Scheduler(RunningSubmissionConfig.GET_RUNNING_SUBMISSION_INFO_RATE_MILLIS, new Scheduler.Runner() {
            @Override
            protected void run() throws Throwable {
                String submissionId = in.readUTF();
                RunningSubmission runningSubmission = runningSubmissionRepository.getById(new Id(submissionId));
                SocketOutput output;
                if (runningSubmission == null) {
                    output = null;
                } else {
                    output = SocketOutput.builder()
                            .coderId(runningSubmission.getCoderId())
                            .problemId(runningSubmission.getProblemId())
                            .submittedAt(runningSubmission.getSubmittedAt())
                            .judgingTestCase(runningSubmission.getJudgingTestCase())
                            .totalTestCases(runningSubmission.getTotalTestCases())
                            .build();
                }
                try {
                    out.writeUTF(objectMapper.writeValueAsString(output));
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    stop();
                }
            }
        }).start();
    }

    @Override
    public Output getByIdAndCoderId(Id id, Id coderId, String serviceToCreate) {
        RunningSubmission runningSubmission = runningSubmissionRepository.getByIdAndCoderId(id, coderId, serviceToCreate);
        return toOutput(runningSubmission);
    }

    @Override
    public List<Output> getByCoderId(int page, int perPage, Id coderId, String serviceToCreate) {
        return runningSubmissionRepository.getByCoderId(page, perPage, coderId, serviceToCreate)
                .stream()
                .map(item -> toOutput(item))
                .collect(Collectors.toList());
    }

    private Output toOutput(RunningSubmission runningSubmission) {
        return Output.builder()
                .coderId(runningSubmission.getCoderId())
                .problemId(runningSubmission.getProblemId())
                .code(runningSubmission.getCode())
                .programmingLanguage(runningSubmission.getProgrammingLanguage())
                .submittedAt(runningSubmission.getSubmittedAt())
                .judgingTestCase(runningSubmission.getJudgingTestCase())
                .totalTestCases(runningSubmission.getTotalTestCases())
                .build();
    }
}
