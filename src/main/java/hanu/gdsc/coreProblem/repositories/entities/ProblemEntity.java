package hanu.gdsc.coreProblem.repositories.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hanu.gdsc.coreProblem.domains.Difficulty;
import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.domains.ProgrammingLanguage;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "core_problem_problem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProblemEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    private String name;
    private String description;
    private String difficulty;
    @Column(columnDefinition = "VARCHAR(30)")
    private String authorId;
    private long ACsCount;
    private long submissionsCount;
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TestCaseEntity> testCases = new HashSet<>();
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TimeLimitEntity> timeLimits = new HashSet<>();
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MemoryLimitEntity> memoryLimits = new HashSet<>();
    private String allowedProgrammingLanguages;
    @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
    @Version
    private Long version;

    public static ProblemEntity toEntity(Problem problem) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<String> programmingLangs = problem.getAllowedProgrammingLanguages()
                .stream()
                .map(x -> x.toString())
                .collect(Collectors.toList());
        return ProblemEntity.builder()
                .id(problem.getId().toString())
                .version(problem.getVersion())
                .name(problem.getName())
                .description(problem.getDescription())
                .difficulty(problem.getDifficulty().toString())
                .authorId(problem.getAuthor().toString())
                .testCases(problem.getTestCases().stream()
                        .map(testCase -> TestCaseEntity.toEntity(testCase))
                        .collect(Collectors.toSet()))
                .timeLimits(problem.getTimeLimits().stream()
                        .map(timeLimit -> TimeLimitEntity.toEntity(timeLimit))
                        .collect(Collectors.toSet()))
                .memoryLimits(problem.getMemoryLimits().stream()
                        .map(memoryLimit -> MemoryLimitEntity.toEntity(memoryLimit))
                        .collect(Collectors.toSet()))
                .allowedProgrammingLanguages(gson.toJson(programmingLangs))
                .build();
    }

    public static Problem toDomain(ProblemEntity problemEntity) {
        Problem problem = new Problem(new hanu.gdsc.share.domains.Id(problemEntity.getId()), problemEntity.getVersion());
        problem.setName(problemEntity.getName());
        problem.setDescription(problemEntity.getDescription());
        problem.setDifficulty(Difficulty.valueOf(problemEntity.getDifficulty()));
        problem.setAuthor(new hanu.gdsc.share.domains.Id(problemEntity.getAuthorId()));
        problem.setTestCases(problemEntity.getTestCases().stream()
                .map(testCaseEntity -> TestCaseEntity.toDomain(testCaseEntity))
                .collect(Collectors.toList()));
        problem.setMemoryLimits(problemEntity.getMemoryLimits().stream()
                .map(memoryLimitEntity -> MemoryLimitEntity.toDomain(memoryLimitEntity))
                .collect(Collectors.toList()));
        problem.setTimeLimits(problemEntity.getTimeLimits().stream()
                .map(timeLimitEntity -> TimeLimitEntity.toDomain(timeLimitEntity))
                .collect(Collectors.toList()));
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<String> allowedLangs = gson.fromJson(problemEntity.getAllowedProgrammingLanguages(), List.class);
        problem.setAllowedProgrammingLanguages(
                allowedLangs
                        .stream()
                        .map(
                                x -> ProgrammingLanguage.valueOf(x)
                        )
                        .collect(Collectors.toList())
        );
        return problem;
    }
}
