package hanu.gdsc.contest.domains;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentitifedVersioningDomainObject;
import hanu.gdsc.share.exceptions.InvalidInputException;
import hanu.gdsc.share.exceptions.InvalidStateException;

import java.util.*;

public class Contest extends IdentitifedVersioningDomainObject {
    private String name;
    private String description;
    private DateTime startAt;
    private DateTime endAt;
    private Id createdBy;
    private List<ContestProblem> contestProblems;
    private DateTime createdAt;

    private Contest(Id id,
                    long version,
                    String name,
                    String description,
                    DateTime startAt,
                    DateTime endAt,
                    Id createdBy,
                    List<ContestProblem> contestProblems,
                    DateTime createdAt) {
        super(id, version);
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.createdBy = createdBy;
        this.contestProblems = contestProblems;
        this.createdAt = createdAt;
    }

    public static Contest create(String name,
                                 String description,
                                 DateTime startAt,
                                 DateTime endAt,
                                 Id authorId,
                                 List<ContestProblem> problems) throws InvalidInputException {
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
                problems,
                DateTime.now()
        );
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setProblems(List<ContestProblem> problems) throws InvalidInputException {
        validateProblems(problems);
        problems = new ArrayList<>(problems);
        problems.sort(Comparator.comparingInt(ContestProblem::getOrdinal));
        this.contestProblems = problems;
    }

    public static void validateProblems(List<ContestProblem> problems) throws InvalidInputException {
        Set<Integer> ordinalSet = new HashSet<>();
        for (ContestProblem problem : problems) {
            if (problem == null) {
                throw new InvalidInputException("Contest problem cannot be null");
            }
            if (ordinalSet.contains(problem.getOrdinal())) {
                throw new InvalidInputException("Duplicated ordinal: " + problem.getOrdinal());
            }
            ordinalSet.add(problem.getOrdinal());
        }
    }

    public static void validateStartAtEndAt(DateTime startAt, DateTime endAt) throws InvalidInputException {
        if (startAt.isBefore(DateTime.now())) {
            throw new InvalidInputException("Start time must be in the future");
        }
        if (endAt.equals(startAt) || endAt.isBefore(startAt)) {
            throw new InvalidInputException("Endt time must be after start time");
        }
    }

    public void setStartAtAndEndAt(DateTime startAt, DateTime endAt) throws InvalidStateException, InvalidInputException {
        if (started() || ended()) {
            throw new InvalidStateException("Contest is happening or ended, cannot update start time and end time");
        }
        validateStartAtEndAt(startAt, endAt);
        setStartAt(startAt);
        setEndAt(endAt);
    }

    public void removeProblem(int ordinal) throws InvalidInputException {
        for (int i = 0; i < contestProblems.size(); i++) {
            if (contestProblems.get(i).getOrdinal() == ordinal) {
                contestProblems.remove(i);
                return;
            }
        }
        throw new InvalidInputException("Unknown ordinal.");
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
