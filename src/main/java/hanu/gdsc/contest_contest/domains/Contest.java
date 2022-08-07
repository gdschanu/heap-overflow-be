package hanu.gdsc.contest_contest.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;
import hanu.gdsc.share.error.BusinessLogicError;
import hanu.gdsc.share.error.InvalidInputError;

import java.util.*;

public class Contest extends IdentitifedVersioningDomainObject {
    private String name;
    private String description;
    private DateTime startAt;
    private DateTime endAt;
    private Id createdBy;
    private List<ContestProblem> contestProblems;

    private Contest(Id id,
                    long version,
                    String name,
                    String description,
                    DateTime startAt,
                    DateTime endAt,
                    Id createdBy,
                    List<ContestProblem> contestProblems) {
        super(id, version);
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.createdBy = createdBy;
        this.contestProblems = contestProblems;
    }

    public static Contest create(String name,
                                 String description,
                                 DateTime startAt,
                                 DateTime endAt,
                                 Id authorId,
                                 List<ContestProblem> problems) {
        validateStartAtEndAt(startAt, endAt);
        validateProblems(problems);
        problems = new ArrayList<>(problems);
        problems.sort(Comparator.comparingInt(ContestProblem::getOrdinal));
        return new Contest(
                Id.generateRandom(),
                0,
                name,
                description,
                startAt,
                endAt,
                authorId,
                problems
        );
    }

    public void setProblems(List<ContestProblem> problems) {
        validateProblems(problems);
        problems = new ArrayList<>(problems);
        problems.sort(Comparator.comparingInt(ContestProblem::getOrdinal));
        this.contestProblems = problems;
    }

    public static void validateProblems(List<ContestProblem> problems) {
        Set<Integer> ordinalSet = new HashSet<>();
        for (ContestProblem problem : problems) {
            if (problem == null) {
                throw new InvalidInputError("Contest problem cannot be null")
                        ;
            }
            if (ordinalSet.contains(problem.getOrdinal())) {
                throw new InvalidInputError("Duplicated ordinal: " + problem.getOrdinal());
            }
            ordinalSet.add(problem.getOrdinal());
        }
    }

    public static void validateStartAtEndAt(DateTime startAt, DateTime endAt) {
        if (startAt.isBefore(DateTime.now())) {
            throw new InvalidInputError("Thời gian bắt đầu phải muộn hơn hiện tại.");
        }
        if (endAt.equals(startAt) || endAt.isBefore(startAt)) {
            throw new InvalidInputError("Thời gian kết thúc phải muộn hơn thời gian bắt đầu.");
        }
    }

    public void setStartAtAndEndAt(DateTime startAt, DateTime endAt) {
        if (started() || ended()) {
            throw new BusinessLogicError("Kì thi đang diễn ra hoặc đã kết thúc, không được phép update thời gian.", "CAN_NOT_UPDATE");
        }
        validateStartAtEndAt(startAt, endAt);
        setStartAt(startAt);
        setEndAt(endAt);
    }

    public void removeProblem(int ordinal) {
        for (int i = 0; i < contestProblems.size(); i++) {
            if (contestProblems.get(i).getOrdinal() == ordinal) {
                contestProblems.remove(i);
                return;
            }
        }
        throw new BusinessLogicError("Unknown ordinal.", "UNKNOWN_ORDINAL");
    }

    public ContestProblem getProblem(int ordinal) {
        for (ContestProblem contestProblem : contestProblems) {
            if (contestProblem.getOrdinal() == ordinal) {
                return contestProblem;
            }
        }
        return null;
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

    public List<ContestProblem> getProblems() {
        return Collections.unmodifiableList(contestProblems);
    }
}
