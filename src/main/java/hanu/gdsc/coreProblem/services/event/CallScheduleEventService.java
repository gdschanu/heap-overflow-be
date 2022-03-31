package hanu.gdsc.coreProblem.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import hanu.gdsc.coreProblem.domains.Status;
import hanu.gdsc.coreProblem.repositories.EventRepository;
import hanu.gdsc.coreProblem.repositories.entities.EventEntity;
import hanu.gdsc.coreProblem.services.submissionsCount.UpdateSubmissionCountService;
import hanu.gdsc.share.domains.Id;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CallScheduleEventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UpdateSubmissionCountService updateSubmissionCountService;
    private static final Logger log = LoggerFactory.getLogger(CallScheduleEventService.class);

    @Scheduled(fixedRate = 10000) 
    @Transactional
    public void executeEvent() {
        EventEntity eventEntity = eventRepository.getEventEntity();
        if (eventEntity != null) {
            updateSubmissionCountService.update(
                UpdateSubmissionCountService.Input.builder()
                    .problemId(new Id(eventEntity.getProblemId()))
                    .status(Status.valueOf(eventEntity.getStatus()))
                    .build()
            );
            log.info("updated submissionCount's problem has id: " + eventEntity.getProblemId());
            eventRepository.delete(eventEntity.getId());
        }
        log.info("Not found any event");
    }
}
