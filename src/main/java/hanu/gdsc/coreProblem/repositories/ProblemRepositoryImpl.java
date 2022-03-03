package hanu.gdsc.coreProblem.repositories;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hanu.gdsc.coreProblem.domains.Problem;
import hanu.gdsc.coreProblem.repositories.JPA.ProblemJPARepository;
import hanu.gdsc.coreProblem.repositories.entities.ProblemEntity;

@Repository
public class ProblemRepositoryImpl implements ProblemRepository{
    @Autowired
    private ProblemJPARepository problemJPARepository;
    
    @Override
    public Problem getById(UUID id) {
        ProblemEntity problemEntity = problemJPARepository.getById(id);
        return null;
    }

    @Override
    public void save() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(UUID id) {
        // TODO Auto-generated method stub
        
    }
}
