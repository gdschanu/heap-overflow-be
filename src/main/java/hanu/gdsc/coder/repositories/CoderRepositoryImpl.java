package hanu.gdsc.coder.repositories;

import hanu.gdsc.coder.domains.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Coder> get(int page, int perPage) {
        Page<CoderEntity> coderEntities = coderJpaRepository.findAll(
                PageRequest.of(page, perPage, Sort.by("rank").descending())
        );
        return coderEntities != null ? coderEntities.stream().map(x -> x.toDomain()).collect(Collectors.toList()) : null;
    }
}
