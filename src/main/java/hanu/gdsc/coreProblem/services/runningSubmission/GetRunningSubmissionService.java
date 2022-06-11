package hanu.gdsc.coreProblem.services.runningSubmission;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.coreProblem.config.RunningSubmissionConfig;
import hanu.gdsc.coreProblem.domains.RunningSubmission;
import hanu.gdsc.coreProblem.repositories.runningSubmission.RunningSubmissionRepository;
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

@Service
public class GetRunningSubmissionService {
    private ServerSocket serverSocket;
    private final RunningSubmissionRepository runningSubmissionRepository;
    private final ObjectMapper objectMapper;

    public GetRunningSubmissionService(RunningSubmissionRepository runningSubmissionRepository,
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
    private static class Output {
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
                Output output;
                if (runningSubmission == null) {
                    output = null;
                } else {
                    output = Output.builder()
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
}
