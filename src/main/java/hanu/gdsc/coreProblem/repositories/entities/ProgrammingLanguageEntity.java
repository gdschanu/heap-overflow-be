package hanu.gdsc.coreProblem.repositories.entities;

import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.*;

@Entity
@Table(name = "programmingLanguage")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProgrammingLanguageEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String name;
    @Version
    private Long version;
}
