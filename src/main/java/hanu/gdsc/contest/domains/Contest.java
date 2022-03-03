package hanu.gdsc.contest.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedDomainObject;
import hanu.gdsc.share.error.BusinessLogicError;

import java.util.ArrayList;
import java.util.List;

public class Contest extends IdentitifedDomainObject {
    private String name;
    private String description;
    private DateTime startAt;
    private DateTime endAt;
    private Id author;
    private List<Problem> problems;
    private List<Participant> participants;

    public Contest(Id id, long version, String name, String description, DateTime startAt, DateTime endAt, Id author, List<Problem> problems, List<Participant> participants) {
        super(id, version);
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.author = author;
        this.problems = problems;
        this.participants = participants;
    }

    public static Contest create(String name, String description, DateTime startAt, DateTime endAt, Id authorId) {
        if (startAt.isBefore(DateTime.now())) {
            throw new BusinessLogicError("Thời gian bắt đầu phải muộn hơn hiện tại.");
        }
        if (endAt.isBefore(startAt) || endAt.equals(startAt)) {
            throw new BusinessLogicError("Thời gian kết thúc phải muộn hơn thời gian bắt đầu.");
        }
        return new Contest(
                Id.generateRandom(),
                0,
                name,
                description,
                startAt,
                endAt,
                authorId,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public boolean started() {
        return startAt.isBefore(DateTime.now());
    }

    public boolean ended() {
        return endAt.isBefore(DateTime.now());
    }

    public void addParticipant(Participant participant) {
        if (this.started() || this.ended()) {
            throw new BusinessLogicError("Contest đang diễn ra hoặc đã kết thúc, không thể thêm thí sinh.");
        }
        if (!participants.contains(participant)) {
            participants.add(participant);
        }
    }

}
