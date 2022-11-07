package hanu.gdsc.domain.contest.services.participant;

import hanu.gdsc.domain.coderAuth.models.User;
import hanu.gdsc.domain.coderAuth.models.Username;
import hanu.gdsc.domain.coderAuth.services.user.SearchUserService;
import hanu.gdsc.domain.contest.models.Participant;
import hanu.gdsc.domain.contest.repositories.ParticipantCountRepository;
import hanu.gdsc.domain.contest.repositories.ParticipantRepository;
import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchParticipantService {
    private final ParticipantCountRepository participantCountRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private SearchUserService searchUserService;

    @Builder
    public static class OutputParticipant {
        public String coderId;
        public String contestId;
        public List<String> problemScores;
        public String createAt;
        public String username;
        public double totalScore;
    }

    public List<OutputParticipant> searchByContestId(Id contestId, int page, int perPage) {
        List<Participant> participants = participantRepository.get(contestId, page, perPage);
        List<OutputParticipant> outputParticipants = new ArrayList<>();
        List<User> users = searchUserService.getListUserByCoderIds(participants.stream()
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
                    .problemScores(participant.getProblemScores().stream().map(x -> x.toString()).collect(Collectors.toList()))
                    .createAt(participant.getCreatedAt().toString())
                    .username(username.toString())
                    .totalScore(participant.totalScore())
                    .build());
        }
        return outputParticipants;
    }

    public long countContestParticipant(Id contestId) {
        return participantCountRepository.getByContestId(contestId).getNum();
    }

    public boolean joinedContest(Id coderId, Id contestId) {
        final List<Participant> participants = participantRepository.getByCoderId(coderId);
        if (participants == null) {
            return false;
        } else {
            for (Participant participant : participants) {
                if (participant.getContestId().toString().equals(contestId.toString())) {
                    return true;
                }
            }
            return false;
        }
    }
}
