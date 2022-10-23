package hanu.gdsc.infrastructure.coder.repositories;

import hanu.gdsc.domain.coder.models.Coder;
import hanu.gdsc.domain.coder.repositories.CoderRepository;
import hanu.gdsc.domain.share.models.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CoderRepositoryImpl implements CoderRepository {
    @Autowired
    private CoderJpaRepository coderJpaRepository;

    @Override
    public void save(Coder coder) {
        coderJpaRepository.save(CoderEntity.fromDomains(coder));
    }

    @Override
    public List<Coder> get(int page, int perPage) {
        Page<CoderEntity> coderEntities = coderJpaRepository.findAll(
                PageRequest.of(page, perPage)
        );
        return coderEntities != null ? coderEntities.stream().map(x -> {
                return x.toDomain();
        }).collect(Collectors.toList()) : null;
    }

    @Override
    public Coder getById(Id id) {
        CoderEntity coder = coderJpaRepository.getById(id.toString());
        return coder.toDomain();
    }
}
