package hanu.gdsc.core_problem.repositories.acceptedProblem;

import hanu.gdsc.core_problem.domains.AcceptedProblem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "core_problem_accepted_problem")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AcceptedProblemEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    private String serviceToCreate;

    public static AcceptedProblemEntity fromDomain(AcceptedProblem acceptedProblem) {
        return new AcceptedProblemEntity(
                genId(acceptedProblem.getCoderId(), acceptedProblem.getProblemId()),
                acceptedProblem.getCoderId().toString(),
                acceptedProblem.getProblemId().toString(),
                acceptedProblem.getServiceToCreate()
        );
    }

    public static String genId(hanu.gdsc.share.domains.Id coderId, hanu.gdsc.share.domains.Id problemId) {
        return coderId.toString() + "#" + problemId.toString();
    }


    public AcceptedProblem toDomain() {
        try {
            return new AcceptedProblem(new hanu.gdsc.share.domains.Id(coderId),
                    new hanu.gdsc.share.domains.Id(problemId),
                    serviceToCreate);
        } catch (Exception e) {
            // cannot reach
            return null;
        }
    }
}
