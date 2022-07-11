package hanu.gdsc.core_like.repositories.reaction;

import hanu.gdsc.core_like.domains.Action;
import hanu.gdsc.core_like.domains.Reaction;
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
                    hanu.gdsc.share.domains.Id.class,
                    hanu.gdsc.share.domains.Id.class,
                    Action.class,
                    String.class
            );
            constructor.setAccessible(true);
            Reaction reaction = constructor.newInstance(
                    reactionEntity.getVersion(),
                    new hanu.gdsc.share.domains.Id(reactionEntity.getCoderId()),
                    new hanu.gdsc.share.domains.Id(reactionEntity.getReactedObjectId()),
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
