package hanu.gdsc.practiceProblem_problem.controllers.runningSubmission;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.practiceProblem_problem.config.RunningSubmissionConfig;
import hanu.gdsc.practiceProblem_problem.services.runningSubmission.SearchRunningSubmissionService;
import hanu.gdsc.share.controller.ControllerHandler;
import hanu.gdsc.share.domains.Id;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.net.UnknownHostException;
import java.util.List;

@RestController
@Tag(name = "Practice Problem - Running Submission", description = "Rest-API endpoint for Practice Problem")
public class SearchRunningSubmissionController {
    @Autowired
    private SearchRunningSubmissionService searchRunningSubmissionService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/practiceProblem/runningSubmission")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage,
                                 @RequestParam(required = false, name = "problemId") String problemId,
                                 @RequestParam(required = false, name = "coderId") String coderId) {
        return ControllerHandler.handle(() -> {
            Id problem = problemId == null ? null : new Id(problemId);
            Id coder = coderId == null ? null : new Id(coderId);
            List<hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output> output =
                    searchRunningSubmissionService
                            .getRunningSubmissions(problem, coder, page, perPage);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }

    private SocketIOServer server = null;

    @EventListener(ApplicationReadyEvent.class)
    public void getRunningSubmissionById() throws UnknownHostException {
        Configuration config = new Configuration();
        config.setHostname(RunningSubmissionConfig.IP);
        config.setPort(RunningSubmissionConfig.PORT);
        config.setOrigin("*");
        server = new SocketIOServer(config);
        server.addEventListener("GET_RUNNING_SUBMISSION", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                hanu.gdsc.core_problem.services.runningSubmission.SearchRunningSubmissionService.Output
                        output = searchRunningSubmissionService.getById(new Id(s));
                if (output != null)
                    socketIOClient.sendEvent("RETURN_RUNNING_SUBMISSION", output);
                else
                    socketIOClient.sendEvent("RETURN_RUNNING_SUBMISSION", s);
            }
        });
        server.start();
    }

    @PreDestroy
    public void destroy() {
        if (server != null)
            server.stop();
    }
}
