package hanu.gdsc.contest_contest.services.participant;

import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.domains.Username;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.contest_contest.domains.ProblemScore;
import hanu.gdsc.contest_contest.repositories.participant.ParticipantRepository;
import hanu.gdsc.core_problem.domains.Problem;
import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetParticipantsService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private UserRepository userRepository;

    @Builder
    public static class OutputParticipant {
        public String coderId;
        public String contestId;
        public int rank;
        public List<String> problemScores;
        public String createAt;
        public String username;
    }
    public List<OutputParticipant> getParticipants(Id contestId, int page, int perPage) {
        List<Participant> participants = participantRepository.get(contestId, page, perPage);
        List<OutputParticipant> outputParticipants = new ArrayList<>();
        // TODO: optimize this
        for(Participant participant : participants) {
            User user = userRepository.getByCoderId(participant.getCoderId());
            Username username = user.getUsername();
            outputParticipants.add(OutputParticipant.builder()
                            .coderId(participant.getCoderId().toString())
                            .contestId(participant.getContestId().toString())
                            .rank(participant.getRank())
                            .problemScores(participant.getProblemScores().stream().map(x -> x.toString()).collect(Collectors.toList()))
                            .createAt(participant.getCreatedAt().toString())
                            .username(username.toString())
                    .build());
        }
        return outputParticipants;
    }
}
