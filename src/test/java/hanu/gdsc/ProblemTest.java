package hanu.gdsc;

import hanu.gdsc.domain.core_problem.models.*;
import hanu.gdsc.infrastructure.core_problem.repositories.problem.ProblemEntity;
import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ProblemTest {
    @Test
    public void r() throws InvalidInputException {
        Problem problem = Problem.create(
                "name",
                "description",
                Id.generateRandom(),
                Arrays.asList(
                        new MemoryLimit.CreateInputML(
                                ProgrammingLanguage.JAVA,
                                new KB(100)
                        ),
                        new MemoryLimit.CreateInputML(
                                ProgrammingLanguage.CPLUSPLUS,
                                new KB(200)
                        )
                ),
                Arrays.asList(
                        new TimeLimit.CreateInputTL(
                                ProgrammingLanguage.JAVA,
                                new Millisecond(1000)
                        ),
                        new TimeLimit.CreateInputTL(
                                ProgrammingLanguage.CPLUSPLUS,
                                new Millisecond(2000)
                        )
                ),
                Arrays.asList(
                        ProgrammingLanguage.JAVA,
                        ProgrammingLanguage.CPLUSPLUS
                ),
                "service"
        );
        ProblemEntity problemEntity = ProblemEntity.toEntity(problem);
        /*
        {
            "timeLimits":[
                {"programmingLanguage":"JAVA","timeLimit":{"value":5000}},
                {"programmingLanguage":"CPLUSPLUS","timeLimit":{"value":5000}}
            ]
        }
        {
            "memoryLimits":[
                {"programmingLanguage":"JAVA","memoryLimit":{"value":60000000.0}},
                {"programmingLanguage":"CPLUSPLUS","memoryLimit":{"value":60000000.0}}
            ]
        }
        */
    }

    @Test
    public void f() {
        ProblemEntity problemEntity = ProblemEntity.builder()
                .id(Id.generateRandom().toString())
                .name("name")
                .description("description")
                .authorId(Id.generateRandom().toString())
                .timeLimits("{\n" +
                        "            \"timeLimits\":[\n" +
                        "                {\"programmingLanguage\":\"JAVA\",\"timeLimit\":{\"value\":1000}},\n" +
                        "                {\"programmingLanguage\":\"CPLUSPLUS\",\"timeLimit\":{\"value\":2000}}\n" +
                        "            ]\n" +
                        "        }")
                .memoryLimits("{\n" +
                        "            \"memoryLimits\":[\n" +
                        "                {\"programmingLanguage\":\"JAVA\",\"memoryLimit\":{\"value\":100.0}},\n" +
                        "                {\"programmingLanguage\":\"CPLUSPLUS\",\"memoryLimit\":{\"value\":200.0}}\n" +
                        "            ]\n" +
                        "        }")
                .allowedProgrammingLanguages("[\"JAVA\",\"CPLUSPLUS\"]")
                .version(0L)
                .serviceToCreate("service")
                .build();
        Problem problem = ProblemEntity.toDomain(problemEntity);
    }
}
