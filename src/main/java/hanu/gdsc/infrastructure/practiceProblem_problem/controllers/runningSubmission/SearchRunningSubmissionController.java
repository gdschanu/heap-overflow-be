package hanu.gdsc.infrastructure.practiceProblem_problem.controllers.runningSubmission;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.practiceProblem_problem.services.runningSubmission.SearchPracticeProblemRunningSubmissionService;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.infrastructure.share.controller.ControllerHandler;
import hanu.gdsc.infrastructure.share.controller.Socket;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.List;

@RestController(value = "hanu.gdsc.infrastructure.practiceProblem_problem.controllers.runningSubmission.SearchRunningSubmissionController")
@Tag(name = "Practice Problem - Running Submission", description = "Rest-API endpoint for Practice Problem")
public class SearchRunningSubmissionController {
    @Autowired
    private SearchPracticeProblemRunningSubmissionService searchPracticeProblemRunningSubmissionService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Socket socket;

    @GetMapping("/practiceProblem/runningSubmission")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage,
                                 @RequestParam(required = false, name = "problemId") String problemId,
                                 @RequestParam(required = false, name = "coderId") String coderId) {
        return ControllerHandler.handle(() -> {
            Id problem = problemId == null ? null : new Id(problemId);
            Id coder = coderId == null ? null : new Id(coderId);
            List<SearchPracticeProblemRunningSubmissionService.Output> output =
                    searchPracticeProblemRunningSubmissionService
                            .getRunningSubmissions(problem, coder, page, perPage);
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getRunningSubmissionById() throws UnknownHostException {
        socket.addEventListener("PRACTICE_PROBLEM.GET_RUNNING_SUBMISSION", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                SearchPracticeProblemRunningSubmissionService.Output
                        output = searchPracticeProblemRunningSubmissionService.getById(new Id(s));
                if (output != null)
                    socketIOClient.sendEvent("PRACTICE_PROBLEM.RETURN_RUNNING_SUBMISSION", output);
                else
                    socketIOClient.sendEvent("PRACTICE_PROBLEM.RETURN_RUNNING_SUBMISSION", s);
            }
        });
    }
}
