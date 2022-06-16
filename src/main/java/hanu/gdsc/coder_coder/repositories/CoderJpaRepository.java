package hanu.gdsc.coder_coder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.share.domains.Id;

public interface CoderJpaRepository extends JpaRepository<CoderEntity, Id> {
}
