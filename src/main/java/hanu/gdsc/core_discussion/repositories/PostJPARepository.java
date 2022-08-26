package hanu.gdsc.core_discussion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJPARepository extends JpaRepository<PostEntity, String> {

    PostEntity getByIdAndServiceToCreate(String id, String serviceToCreate);
}
