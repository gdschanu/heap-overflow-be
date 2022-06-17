package hanu.gdsc.practiceProblem_problem.repositories.problem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import hanu.gdsc.practiceProblem_problem.repositories.category.CategoryEntity;
import hanu.gdsc.practiceProblem_problem.repositories.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import hanu.gdsc.practiceProblem_problem.domains.Category;
import hanu.gdsc.practiceProblem_problem.domains.Problem;
import hanu.gdsc.share.domains.Id;

@Component(value = "PracticeProblem.ProblemRepositoryImpl")
public class ProblemRepositoryImpl implements ProblemRepository {
    @Autowired
    private PPProblemJPARepository PPProblemJpaRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void create(Problem practiceProblem) {
        PPProblemEntity ppproblemEntity = PPProblemEntity.toEntity(practiceProblem);
        Set<CategoryEntity> categoriesEntity = new HashSet<>();
        for(Id categoryId : practiceProblem.getCategoryIds()) {
            Category category = categoryRepository.getById(categoryId);
            categoriesEntity.add(CategoryEntity.toEntity(category));
        }
        ppproblemEntity.setCategory(categoriesEntity);
        PPProblemJpaRepository.save(ppproblemEntity);
    }

    @Override
    public Problem getById(Id id) {
        try {
            PPProblemEntity PPProblemEntity = PPProblemJpaRepository.getById(id.toString());
            return PPProblemEntity.toDomain(PPProblemEntity);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Problem> get(int page, int perPage) {
        Pageable pageable = PageRequest.of(page , perPage);
        Page<PPProblemEntity> entities =  PPProblemJpaRepository.findAll(pageable);
        return entities.getContent()
                .stream().map(
                        e -> PPProblemEntity.toDomain(e)
                ).collect(Collectors.toList());
    }

    @Override
    public void update(Problem problem) {
        PPProblemEntity ppproblemEntity = PPProblemEntity.toEntity(problem);
        Set<CategoryEntity> categoriesEntity = new HashSet<>();
        for (Id categoryId : problem.getCategoryIds()) {
            Category category = categoryRepository.getById(categoryId);
            categoriesEntity.add(CategoryEntity.toEntity(category));
        }
        ppproblemEntity.setCategory(categoriesEntity);
        PPProblemJpaRepository.save(ppproblemEntity);
    }


}
