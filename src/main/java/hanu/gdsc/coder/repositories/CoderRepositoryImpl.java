package hanu.gdsc.coder.repositories;

import hanu.gdsc.coder.domains.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CoderRepositoryImpl implements CoderRepository {
    @Autowired
    private CoderJpaRepository coderJpaRepository;

    @Override
    public void create(Coder coder) {
        coderJpaRepository.save(CoderEntity.fromDomains(coder));
    }

    @Override
    public List<Coder> getAll() {
        List<CoderEntity> coderEntities = coderJpaRepository.findAll();
        return coderEntities.stream().map(x -> x.toDomain()).collect(Collectors.toList());
    }
}
