package hanu.gdsc.infrastructure.core_like.repositories.reactedObject;

import java.lang.reflect.Constructor;

import javax.persistence.*;

import hanu.gdsc.domain.core_like.models.ReactedObject;
import lombok.*;

@Entity
@Table(name = "core_like_reacted_object")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReactedObjectEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Version
    private long version;
    private long likeCount;
    private long dislikeCount;
    private String serviceToCreate;

    public static ReactedObjectEntity toEntity(final ReactedObject reactedObject) {
        return ReactedObjectEntity.builder()
                .id(reactedObject.getId().toString())
                .version(reactedObject.getVersion())
                .likeCount(reactedObject.getLikeCount())
                .dislikeCount(reactedObject.getDislikeCount())
                .serviceToCreate(reactedObject.getServiceToCreate())
                .build();
                
    }

    public static ReactedObject toDomain(final ReactedObjectEntity reactedObjectEntity) {
        try {
            Constructor<ReactedObject> constructor = ReactedObject.class.getDeclaredConstructor(
                hanu.gdsc.domain.share.models.Id.class,
                Long.TYPE,
                Long.TYPE,
                Long.TYPE,
                String.class
            );
            constructor.setAccessible(true);
            ReactedObject reactedObject = constructor.newInstance(
                new hanu.gdsc.domain.share.models.Id(reactedObjectEntity.getId()),
                reactedObjectEntity.getVersion(),
                reactedObjectEntity.getLikeCount(),
                reactedObjectEntity.getDislikeCount(),
                reactedObjectEntity.getServiceToCreate()
            );
            return reactedObject;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }
}
