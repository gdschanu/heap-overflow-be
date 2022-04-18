package hanu.gdsc.practiceProblem.repositories;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import hanu.gdsc.practiceProblem.domains.Category;
import hanu.gdsc.practiceProblem.domains.Problem;
import hanu.gdsc.practiceProblem.repositories.JPA.PracticeProblemJPARepository;
import hanu.gdsc.practiceProblem.repositories.entities.CategoryEntity;
import hanu.gdsc.practiceProblem.repositories.entities.PracticeProblemProblemEntity;
import hanu.gdsc.share.domains.Id;

@Component(value = "PracticeProblem.ProblemRepositoryImpl")
public class ProblemRepositoryImpl implements ProblemRepository {
    @Autowired
    private PracticeProblemJPARepository practiceProblemJpaRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void create(Problem practiceProblem) {
        PracticeProblemProblemEntity practiceProblemProblemEntity = PracticeProblemProblemEntity.toEntity(practiceProblem);
        Set<CategoryEntity> categoriesEntity = new HashSet<>();
        for(Id categoryId : practiceProblem.getCategoryIds()) {
            Category category = categoryRepository.getById(categoryId);
            categoriesEntity.add(CategoryEntity.toEntity(category));
        }
        practiceProblemProblemEntity.setCategory(categoriesEntity);
        practiceProblemJpaRepository.save(practiceProblemProblemEntity);
    }

    @Override
    public Problem getById(Id id) {
        try {
            PracticeProblemProblemEntity practiceProblemProblemEntity = practiceProblemJpaRepository.getById(id.toString());
            return PracticeProblemProblemEntity.toDomain(practiceProblemProblemEntity);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Problem> get(int page, int perPage) {
        Pageable pageable = PageRequest.of(page , perPage);
        Page<PracticeProblemProblemEntity> entities =  practiceProblemJpaRepository.findAll(pageable);
        return entities.getContent()
                .stream().map(
                        e -> PracticeProblemProblemEntity.toDomain(e)
                ).collect(Collectors.toList());
    }

    @Override
    public void update(Problem problem) {
        PracticeProblemProblemEntity practiceProblemProblemEntity = PracticeProblemProblemEntity.toEntity(problem);
        Set<CategoryEntity> categoriesEntity = new HashSet<>();
        for (Id categoryId : problem.getCategoryIds()) {
            Category category = categoryRepository.getById(categoryId);
            categoriesEntity.add(CategoryEntity.toEntity(category));
        }
        practiceProblemProblemEntity.setCategory(categoriesEntity);
        practiceProblemJpaRepository.save(practiceProblemProblemEntity);
    }


}
