package hanu.gdsc.practiceProblem_problem.repositories.progress;

import hanu.gdsc.practiceProblem_problem.domains.Progress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "practice_problem_progress")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProgressEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;
    private int solvedEasyProblems;
    private int solvedHardProblems;
    private int solvedMediumProblems;
    @Version
    private long version;

    public static ProgressEntity fromDomain(Progress progress) {
        return new ProgressEntity(
                progress.getCoderId().toString(),
                progress.getSolvedEasyProblems(),
                progress.getSolvedHardProblems(),
                progress.getSolvedMediumProblems(),
                progress.getVersion()
        );
    }

    public Progress toDomain() {
        try {
            Constructor<Progress> constructor = Progress.class.getDeclaredConstructor(
                    hanu.gdsc.share.domains.Id.class,
                    Integer.TYPE,
                    Integer.TYPE,
                    Integer.TYPE,
                    Long.TYPE
            );
            constructor.setAccessible(true);
            return constructor.newInstance(
                    new hanu.gdsc.share.domains.Id(coderId),
                    solvedEasyProblems,
                    solvedHardProblems,
                    solvedMediumProblems,
                    version
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
