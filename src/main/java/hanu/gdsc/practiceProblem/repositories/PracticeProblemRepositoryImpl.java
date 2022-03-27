package hanu.gdsc.practiceProblem.repositories;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import hanu.gdsc.coreProblem.repositories.entities.ProblemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import hanu.gdsc.practiceProblem.domains.Category;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.JPA.PracticeProblemJPARepository;
import hanu.gdsc.practiceProblem.repositories.entities.CategoryEntity;
import hanu.gdsc.practiceProblem.repositories.entities.PracticeProblemEntity;
import hanu.gdsc.share.domains.Id;

@Repository
public class PracticeProblemRepositoryImpl implements PracticeProblemRepository{
    @Autowired
    private PracticeProblemJPARepository practiceProblemJpaRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void create(Problem practiceProblem) {
        PracticeProblemEntity practiceProblemEntity = PracticeProblemEntity.toEntity(practiceProblem);
        Set<CategoryEntity> categoriesEntity = new HashSet<>();
        for(Id categoryId : practiceProblem.getCategoryIds()) {
            Category category = categoryRepository.getById(categoryId);
            categoriesEntity.add(CategoryEntity.toEntity(category));
        }
        practiceProblemEntity.setCategory(categoriesEntity);
        practiceProblemJpaRepository.save(practiceProblemEntity);
    }

    @Override
    public Problem getById(Id id) {
        try {
            PracticeProblemEntity practiceProblemEntity = practiceProblemJpaRepository.getById(id.toString());
            return PracticeProblemEntity.toDomain(practiceProblemEntity);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Problem> get(int page, int perPage) {
        Pageable pageable = PageRequest.of(page , perPage);
        Page<PracticeProblemEntity> entities =  practiceProblemJpaRepository.findAll(pageable);
        return entities.getContent()
                .stream().map(
                        e -> PracticeProblemEntity.toDomain(e)
                ).collect(Collectors.toList());
    }


}
