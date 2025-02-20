package com.vantu.project.product;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.vantu.project.model.Category;
import com.vantu.project.repository.CategoryRepository;

@DataJpaTest
//test với dữ liệu thực, nghĩa là mọi thao tác test đều vs dữ liệu thực
@AutoConfigureTestDatabase(replace = Replace.NONE)
//hủy rollback, nghĩa là mọi thao tác đều tác động thực lên db
@Rollback(false)
public class CategoryRepositoryTests {

	@Autowired
	private CategoryRepository categoryRepository;
	
    @Test
    public void testAddCategory() {
        List<String> categoryNames = List.of(
            "Điện thoại",
            "Laptop", 
            "PC",
            "Máy tính bảng",
            "Màn hình",
            "Bàn phím",
            "Tai nghe",
            "Máy ảnh",
            "Camera"
        );

        for (String name : categoryNames) {
            Category category = new Category();
            category.setName(name);
            categoryRepository.save(category);
        }

        System.out.println("Đã thêm danh mục thành công!");
    }
}
