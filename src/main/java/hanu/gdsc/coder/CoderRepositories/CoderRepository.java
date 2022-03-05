package hanu.gdsc.coder.CoderRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hanu.gdsc.coder.Domains.Coder;
import hanu.gdsc.share.domains.Id;

public interface CoderRepository extends JpaRepository<Coder, Id> {

    List<Coder> searchById(Id id);
    
}
