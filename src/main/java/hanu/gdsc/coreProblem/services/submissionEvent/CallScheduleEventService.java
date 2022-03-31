package hanu.gdsc.coreProblem.services.submissionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.SubmissionEvent;
import hanu.gdsc.coreProblem.services.submissionsCount.UpdateSubmissionCountService;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CallScheduleEventService {
    @Autowired
    private SearchSubmissionEventService searchSubmissionEventService;
    @Autowired
    private DeleteSubmissionEventService deleteSubmissionEventService;
    @Autowired
    private UpdateSubmissionCountService updateSubmissionCountService;
    private static final Logger log = LoggerFactory.getLogger(CallScheduleEventService.class);

    @Scheduled(fixedRate = 10000) 
    @Transactional
    public void executeEvent() {
            SubmissionEvent submissionEvent = searchSubmissionEventService.getSubmissionEvent();
            if (submissionEvent != null) {
                updateSubmissionCountService.update(
                    UpdateSubmissionCountService.Input.builder()
                        .problemId(submissionEvent.getProblemId())
                        .status(submissionEvent.getStatus())
                        .build()
                );
                log.info("updated submissionCount's problem has id: " + submissionEvent.getProblemId().toString());
                deleteSubmissionEventService.deleteById(submissionEvent.getId());
            }
            log.info("Not have any submissionEvent");
    }
}
