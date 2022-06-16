package hanu.gdsc.practiceProblemSubdomain.problemContext.repositories.problem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import hanu.gdsc.practiceProblemSubdomain.problemContext.repositories.category.CategoryEntity;
import hanu.gdsc.practiceProblemSubdomain.problemContext.repositories.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import hanu.gdsc.practiceProblemSubdomain.problemContext.domains.Category;
import hanu.gdsc.practiceProblemSubdomain.problemContext.domains.Problem;
import hanu.gdsc.share.domains.Id;

@Component(value = "PracticeProblem.ProblemRepositoryImpl")
public class ProblemRepositoryImpl implements ProblemRepository {
    @Autowired
    private ProblemJPARepository problemJpaRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void create(Problem practiceProblem) {
        ProblemEntity problemEntity = ProblemEntity.toEntity(practiceProblem);
        Set<CategoryEntity> categoriesEntity = new HashSet<>();
        for(Id categoryId : practiceProblem.getCategoryIds()) {
            Category category = categoryRepository.getById(categoryId);
            categoriesEntity.add(CategoryEntity.toEntity(category));
        }
        problemEntity.setCategory(categoriesEntity);
        problemJpaRepository.save(problemEntity);
    }

    @Override
    public Problem getById(Id id) {
        try {
            ProblemEntity problemEntity = problemJpaRepository.getById(id.toString());
            return ProblemEntity.toDomain(problemEntity);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Problem> get(int page, int perPage) {
        Pageable pageable = PageRequest.of(page , perPage);
        Page<ProblemEntity> entities =  problemJpaRepository.findAll(pageable);
        return entities.getContent()
                .stream().map(
                        e -> ProblemEntity.toDomain(e)
                ).collect(Collectors.toList());
    }

    @Override
    public void update(Problem problem) {
        ProblemEntity problemEntity = ProblemEntity.toEntity(problem);
        Set<CategoryEntity> categoriesEntity = new HashSet<>();
        for (Id categoryId : problem.getCategoryIds()) {
            Category category = categoryRepository.getById(categoryId);
            categoriesEntity.add(CategoryEntity.toEntity(category));
        }
        problemEntity.setCategory(categoriesEntity);
        problemJpaRepository.save(problemEntity);
    }


}
