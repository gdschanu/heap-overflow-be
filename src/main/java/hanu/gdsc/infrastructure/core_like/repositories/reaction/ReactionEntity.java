package hanu.gdsc.infrastructure.core_like.repositories.reaction;

import hanu.gdsc.domain.core_like.models.Action;
import hanu.gdsc.domain.core_like.models.Reaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Constructor;

@Entity
@Table(name = "core_like_reaction")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReactionEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(50)")
    private String id;
    @Version
    private long version;
    private String coderId;
    private String reactedObjectId;
    private String action;
    private String serviceToCreate;

    public static ReactionEntity toEntity(Reaction reaction) {
        return ReactionEntity.builder()
                .id(reaction.getCoderId().toString() + "#" + reaction.getReactedObjectId().toString())
                .coderId(reaction.getCoderId().toString())
                .reactedObjectId(reaction.getReactedObjectId().toString())
                .action(reaction.getAction().toString())
                .version(reaction.getVersion())
                .serviceToCreate(reaction.getServiceToCreate())
                .build();
    }

    public static Reaction toDomain(ReactionEntity reactionEntity) {
        try {
            Constructor<Reaction> constructor = Reaction.class.getDeclaredConstructor(
                    Long.TYPE,
                    hanu.gdsc.domain.share.models.Id.class,
                    hanu.gdsc.domain.share.models.Id.class,
                    Action.class,
                    String.class
            );
            constructor.setAccessible(true);
            Reaction reaction = constructor.newInstance(
                    reactionEntity.getVersion(),
                    new hanu.gdsc.domain.share.models.Id(reactionEntity.getCoderId()),
                    new hanu.gdsc.domain.share.models.Id(reactionEntity.getReactedObjectId()),
                    Action.valueOf(reactionEntity.getAction()),
                    reactionEntity.serviceToCreate
            );
            return reaction;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
