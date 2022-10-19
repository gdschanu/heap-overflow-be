package hanu.gdsc.core_category.services;

import hanu.gdsc.core_category.domains.Category;
import hanu.gdsc.core_category.repositories.CategoryRepository;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

//@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /*
    Vấn đề:
    + "serviceToCreate" là do thằng call service này truyền vào, nên phải nhận từ tham số của hàm
    + hàm create chỉ cần trả về Id vừa được tạo, ko cần trả về cả object Category
    + Chú ý: data tầng ở tầng core luôn được định danh bằng cả id lẫn serviceToCreate
    Sửa lại như sau:
        public Id create(String name, String serviceToCreate) {
            // bên trong hàm Category.create(...) sẽ tự gen id bằng cách gọi Id.generateRandom()
            Category category = Category.create(name, serviceToCreate);
            categoryRepository.save(category);
            return category.getId();
        }
     */

    public Id create(String name, String serviceToCreate) throws InvalidInputException {
        Category category = Category.create(name, serviceToCreate);
        categoryRepository.save(category);
        return category.getId();
    }

    /*
    Vấn đề:
    + Ko cần phải query category lên xong rồi mới delete, vì ko thừa thãi
    + Cần thêm "serviceToCreate" vào trong điều kiện query để xóa
    + Đây là hàm void, k cần trả về data gì cả, có lỗi thì thì throw exception
    + Chú ý: data tầng ở tầng core luôn được định danh bằng cả id lẫn serviceToCreate
    Sửa lại như sau:
        public void delete(Id id, String serviceToCreate) {
            // bên trong hàm delete này, sẽ gọi vào hàm deleteByIdAndServiceToCreate của JPA
            categoryRepository.delete(id, serviceToCreate);
        }
     */
    @Override
    public void delete(Id id, String serviceToCreate) throws NoSuchFieldException {
        categoryRepository.deleteById(id, serviceToCreate);
    }


}
