package hanu.gdsc.contest_contest.services.participant;

import hanu.gdsc.coderAuth.domains.User;
import hanu.gdsc.coderAuth.domains.Username;
import hanu.gdsc.coderAuth.repositories.user.UserRepository;
import hanu.gdsc.coderAuth.services.GetUserService;
import hanu.gdsc.contest_contest.domains.Participant;
import hanu.gdsc.contest_contest.repositories.participant.ParticipantRepository;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetParticipantsService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private GetUserService getUserService;

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
        List<User> users = getUserService.getListUserByCoderIds(participants.stream()
                .map(x -> x.getCoderId()).collect(Collectors.toList()));
        HashMap<Id, Username> map = new HashMap<>();
        for(User user : users) {
            map.put(user.getId(), user.getUsername());
        }
        for(Participant participant : participants) {
            Username username = map.get(participant.getCoderId());
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
