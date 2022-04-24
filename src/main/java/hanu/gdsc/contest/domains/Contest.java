package hanu.gdsc.contest.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;
import hanu.gdsc.share.error.BusinessLogicError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Contest extends IdentitifedVersioningDomainObject {
    private String name;
    private String description;
    private DateTime startAt;
    private DateTime endAt;
    private Id createdBy;
    private List<Problem> problems;

    public Contest(Id id, long version, String name, String description, DateTime startAt, DateTime endAt, Id createdBy, List<Problem> problems) {
        super(id, version);
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.createdBy = createdBy;
        this.problems = problems;
    }

    public static Contest create(String name, String description, DateTime startAt, DateTime endAt, Id authorId) {
        if (startAt.isBefore(DateTime.now())) {
            throw new BusinessLogicError("Thời gian bắt đầu phải muộn hơn hiện tại.", "INVALID_STARTDATE");
        }
        if (endAt.isBefore(startAt) || endAt.equals(startAt)) {
            throw new BusinessLogicError("Thời gian kết thúc phải muộn hơn thời gian bắt đầu.", "INVALID_ENDDATE");
        }
        return new Contest(
                Id.generateRandom(),
                0,
                name,
                description,
                startAt,
                endAt,
                authorId,
                new ArrayList<>()
        );
    }

    public void setStartAtAndEndAt(DateTime startAt, DateTime endAt) {
        if (started() || ended()) {
            throw new BusinessLogicError("Kì thi đang diễn ra hoặc đã kết thúc, không được phép update thời gian.", "CAN_NOT_UPDATE");
        }
        if (startAt.isBefore(DateTime.now())) {
            throw new BusinessLogicError("Thời gian bắt đầu phải muộn hơn hiện tại.", "INVALID_STARTDATE");
        }
        if (endAt.equals(startAt) || endAt.isBefore(startAt)) {
            throw new BusinessLogicError("Thời gian kết thúc phải muộn hơn thời gian bắt đầu.", "INVALID_ENDDATE");
        }
        setStartAt(startAt);
        setEndAt(endAt);
    }

    public void addProblem(Problem probToAdd) {
        for (Problem addedProb : problems) {
            if (addedProb.getOrdinal() == probToAdd.getOrdinal()) {
                throw new BusinessLogicError("Duplicate problem ordinal.", "DUPLICATE_PROBLEM_ORDINAL");
            }
            if (addedProb.getCoreProblemId().equals(probToAdd.getCoreProblemId())) {
                throw new BusinessLogicError("Duplicate problem.", "DUPLICATE_CORE_PROBLEM_ID");
            }
        }
        problems.add(probToAdd);
    }

    public void removeProblem(int ordinal) {
        for (int i = 0; i < problems.size(); i++) {
            if (problems.get(i).getOrdinal() == ordinal) {
                problems.remove(i);
                return;
            }
        }
        throw new BusinessLogicError("Unknown ordinal.", "UNKNOWN_ORDINAL");
    }

    private void setStartAt(DateTime startAt) {
        this.startAt = startAt;
    }

    private void setEndAt(DateTime endAt) {
        this.endAt = endAt;
    }

    public boolean started() {
        return startAt.isBefore(DateTime.now());
    }

    public boolean ended() {
        return endAt.isBefore(DateTime.now());
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getStartAt() {
        return startAt;
    }

    public DateTime getEndAt() {
        return endAt;
    }

    public Id getCreatedBy() {
        return createdBy;
    }

    public List<Problem> getProblems() {
        return Collections.unmodifiableList(problems);
    }
}
