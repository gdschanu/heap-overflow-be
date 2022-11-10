package hanu.gdsc.infrastructure.contest.controllers.runningSubmission;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.domain.contest.services.runningSubmission.SearchContestRunningSubmissionService;
import hanu.gdsc.domain.share.exceptions.NotFoundException;
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

@RestController
@Tag(name = "Contest", description = "Rest-API endpoint for Contest")
public class SearchRunningSubmissionController {
    @Autowired
    private SearchContestRunningSubmissionService searchContestRunningSubmissionService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Socket socket;

    @GetMapping("/contest/runningSubmission")
    public ResponseEntity<?> get(@RequestParam int page, @RequestParam int perPage,
                                 @RequestParam(required = false, name = "contestId") String contestId,
                                 @RequestParam(required = false, name = "contestProblemOrdinal") Integer contestProblemOrdinal,
                                 @RequestParam(required = false, name = "coderId") String coderId) {
        return ControllerHandler.handle(() -> {
            Id contest = contestId == null ? null : new Id(contestId);
            Id coder = coderId == null ? null : new Id(coderId);
            List<SearchContestRunningSubmissionService.Output> output =
                    searchContestRunningSubmissionService
                            .getRunningSubmissions(
                                    contest,
                                    contestProblemOrdinal,
                                    coder,
                                    page,
                                    perPage
                            );
            return new ControllerHandler.Result(
                    "Success",
                    output
            );
        });
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getRunningSubmissionById() throws UnknownHostException {
        socket.addEventListener("CONTEST.GET_RUNNING_SUBMISSION", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                SearchContestRunningSubmissionService.Output output = null;
                try {
                    final String contestId = s.split(",")[0];
                    final String contestProblemOrdinal = s.split(",")[1];
                    output = searchContestRunningSubmissionService.getById(
                            new Id(contestId),
                            Integer.parseInt(contestProblemOrdinal)
                    );
                } catch (NotFoundException e) {
                    output = null;
                }
                if (output != null)
                    socketIOClient.sendEvent("CONTEST.RETURN_RUNNING_SUBMISSION", output);
                else
                    socketIOClient.sendEvent("CONTEST.RETURN_RUNNING_SUBMISSION", s);
            }
        });
    }
}
