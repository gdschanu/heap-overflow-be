package hanu.gdsc.domain.core_category.models;

import hanu.gdsc.domain.share.models.Id;
import hanu.gdsc.domain.share.exceptions.InvalidInputException;

public class Category {
    /*
    Xóa cái annotation này đi, annotation này chỉ dùng cho entity dưới repo
     */
    private Id id;
    private String name;

    private String serviceToCreate;


    /*
    Set private constructor này => để đảm bảo đúng nghiệp vụ
    - Nghiệp vụ của category là khi tạo mới:
        + id phải được generate tự động
        + name not null
        + serviceToCreate not null
    => bản chất khi tạo Category, chỉ cho truyền vào name và serviceToCreate, ko cho phép truyền vào id

    Sau khi đã set private constructor này, chỉ dùng reflection ở tầng repo để truy cập constructor này
    tạo Category trả về cho service vì data khi đã lưu xuống db thì đảm bảo đã đúng nghiệp vụ, khi get lên
    chỉ cần set vào
     */
    private Category(Id id, String name, String serviceToCreate) {
        this.id = id;
        this.name = name;
        this.serviceToCreate = serviceToCreate;
    }

    /*
    Sử dụng static method này để tạo mới Category trên tầng service.
    Đây là cách duy nhất để tạo Category trên tầng service (sau khi đã private constructor trên)
    Tuy nhiên còn đang thiếu serviceToCreate, và validate input. Sửa lại như sau:
        public static Category create(String name, String serviceToCreate) throws InvalidInputException {
            if (name == null) {
                throw new InvalidInputException("name must be not null");
            }
            if (serviceToCreate == null) {
                throw new InvalidInputException("serviceToCreate must be not null");
            }
            return new Category(Id.generateRandom(), name, serviceToCreate);
        }
     */

    public static Category create(String name, String serviceToCreate) throws InvalidInputException {
        if (name == null) {
            throw new InvalidInputException("name must be not null");
        }
        if (serviceToCreate == null) {
            throw new InvalidInputException("serviceToCreate must be not null");
        }
        return new Category(Id.generateRandom(), name, serviceToCreate);
    }

    public Id getId() {
        return id;
    }

    /*
    Xóa hàm setter này, khi Category đã đc khởi tạo, ko đc phép thay đổi id
     */

    public String getName() {
        return name;
    }

    public String getServiceToCreate() {
        return serviceToCreate;
    }

    /*
    Xóa hàm setter này, khi Category đã đc khởi tạo, ko đc phép thay đổi serviceToCreate
     */

    public void setName(String name) {
        this.name = name;
    }
}
