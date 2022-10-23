package hanu.gdsc.infrastructure.core_problem.repositories.problem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hanu.gdsc.domain.core_problem.models.MemoryLimit;
import hanu.gdsc.domain.core_problem.models.Problem;
import hanu.gdsc.domain.core_problem.models.ProgrammingLanguage;
import hanu.gdsc.domain.core_problem.models.TimeLimit;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Constructor;
import java.util.List;
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
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    @Column(columnDefinition = "VARCHAR(30)")
    private String authorId;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String timeLimits;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String memoryLimits;
    private String allowedProgrammingLanguages;
    @Column(name = "version", columnDefinition = "integer DEFAULT 0", nullable = false)
    @Version
    private Long version;
    private String serviceToCreate;

    @AllArgsConstructor
    @NoArgsConstructor
    private static class TimeLimitsWrapper {
        public List<TimeLimit> timeLimits;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private static class MemLimitsWrapper {
        public List<MemoryLimit> memoryLimits;
    }

    public static ProblemEntity toEntity(Problem problem) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<String> programmingLangs = problem.getAllowedProgrammingLanguages()
                .stream()
                .map(x -> x.toString())
                .collect(Collectors.toList());
        ProblemEntity problemEntity = ProblemEntity.builder()
                .id(problem.getId().toString())
                .version(problem.getVersion())
                .name(problem.getName())
                .description(problem.getDescription())
                .authorId(problem.getAuthor().toString())
                .timeLimits(gson.toJson(new TimeLimitsWrapper(problem.getTimeLimits())))
                .memoryLimits(gson.toJson(new MemLimitsWrapper(problem.getMemoryLimits())))
                .allowedProgrammingLanguages(gson.toJson(programmingLangs))
                .serviceToCreate(problem.getServiceToCreate())
                .build();
        return problemEntity;
    }

    public static Problem toDomain(ProblemEntity problemEntity) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        List<String> allowedLangs = gson.fromJson(problemEntity.getAllowedProgrammingLanguages(), List.class);
        try {
        Constructor<Problem> constructor = Problem.class.getDeclaredConstructor(
                hanu.gdsc.domain.share.models.Id.class,
                Long.TYPE, 
                String.class,
                String.class,
                hanu.gdsc.domain.share.models.Id.class,
                List.class,
                List.class,
                List.class,
                String.class
        );
        constructor.setAccessible(true);
        Problem problem = constructor.newInstance(
                new hanu.gdsc.domain.share.models.Id(problemEntity.getId()),
                problemEntity.getVersion(),
                problemEntity.getName(),
                problemEntity.getDescription(),
                new hanu.gdsc.domain.share.models.Id(problemEntity.getAuthorId()),
                gson.fromJson(problemEntity.getMemoryLimits(), MemLimitsWrapper.class).memoryLimits,
                gson.fromJson(problemEntity.getTimeLimits(), TimeLimitsWrapper.class).timeLimits,
                allowedLangs
                        .stream()
                        .map(
                                x -> ProgrammingLanguage.valueOf(x)
                        )
                        .collect(Collectors.toList()),
                problemEntity.getServiceToCreate()
        );
        return problem;
        } catch (Exception e) {
                e.printStackTrace();
                throw new Error(e);
        }
    }
}
