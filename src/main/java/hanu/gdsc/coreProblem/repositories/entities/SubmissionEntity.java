package hanu.gdsc.coreProblem.repositories.entities;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import hanu.gdsc.coreProblem.domains.Submission;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "submission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Version
    private long version;
    @Column(columnDefinition = "BINARY(16)")
    private UUID problemId;
    private String programmingLanguage;
    private long runTimeInMillis;
    private double memoryInKB;
    private String submittedAtInZonedDateTime;
    private String code;
    private String status;
    @Column(columnDefinition = "BINARY(16)")
    private UUID failedTestCaseId;
    private String accessibility;

    public static SubmissionEntity fromDomainObject(Submission submission) {
        return null;
    }
}
