package com.vantu.project.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vantu.project.model.Category;
import com.vantu.project.model.Product;
import com.vantu.project.repository.CategoryRepository;
import com.vantu.project.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SpringBootTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final Random random = new Random();

    @Test
    public void testAddProducts() {
        // Lấy danh sách danh mục từ DB và lưu vào Map để tra cứu nhanh
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            System.out.println("Không có danh mục nào trong DB, hãy chạy test thêm danh mục trước!");
            return;
        }

        Map<String, Category> categoryMap = new HashMap<>();
        for (Category category : categories) {
            categoryMap.put(category.getName(), category);
        }

        // Danh sách sản phẩm theo từng danh mục
        List<Product> products = List.of(
            // Điện thoại
            Product.builder().name("iPhone 15 Pro").brand("Apple").price(new BigDecimal("29990000"))
                .shortDesc("Điện thoại cao cấp").detailDesc("Mô tả chi tiết...").imageUrl("iphone15.jpg")
                .category(categoryMap.get("Điện thoại")).build(),

            Product.builder().name("Galaxy S23 Ultra").brand("Samsung").price(new BigDecimal("25990000"))
                .shortDesc("Flagship Android mạnh mẽ").detailDesc("Mô tả chi tiết...").imageUrl("s23ultra.jpg")
                .category(categoryMap.get("Điện thoại")).build(),

            Product.builder().name("Xiaomi Redmi Note 12").brand("Xiaomi").price(new BigDecimal("4990000"))
                .shortDesc("Điện thoại giá rẻ hiệu năng tốt").detailDesc("Mô tả chi tiết...").imageUrl("redminote12.jpg")
                .category(categoryMap.get("Điện thoại")).build(),

            // Laptop
            Product.builder().name("MacBook Air M2").brand("Apple").price(new BigDecimal("27990000"))
                .shortDesc("Laptop mỏng nhẹ").detailDesc("Mô tả chi tiết...").imageUrl("macbookairm2.jpg")
                .category(categoryMap.get("Laptop")).build(),

            Product.builder().name("Dell XPS 15").brand("Dell").price(new BigDecimal("35990000"))
                .shortDesc("Laptop cho dân thiết kế").detailDesc("Mô tả chi tiết...").imageUrl("dellxps15.jpg")
                .category(categoryMap.get("Laptop")).build(),

            Product.builder().name("Razer Blade 16").brand("Razer").price(new BigDecimal("79990000"))
                .shortDesc("Laptop gaming cao cấp").detailDesc("Mô tả chi tiết...").imageUrl("razerblade.jpg")
                .category(categoryMap.get("Laptop")).build(),

            Product.builder().name("MSI Katana GF66").brand("MSI").price(new BigDecimal("23990000"))
                .shortDesc("Laptop gaming giá tốt").detailDesc("Mô tả chi tiết...").imageUrl("katana.jpg")
                .category(categoryMap.get("Laptop")).build(),

            // PC
            Product.builder().name("PC Gaming RTX 4070").brand("Custom").price(new BigDecimal("45990000"))
                .shortDesc("PC chơi game cấu hình cao").detailDesc("Mô tả chi tiết...").imageUrl("pcgaming.jpg")
                .category(categoryMap.get("PC")).build(),

            // Máy tính bảng
            Product.builder().name("iPad Pro M2").brand("Apple").price(new BigDecimal("27990000"))
                .shortDesc("Máy tính bảng mạnh mẽ").detailDesc("Mô tả chi tiết...").imageUrl("ipadpro.jpg")
                .category(categoryMap.get("Máy tính bảng")).build(),

            Product.builder().name("Samsung Galaxy Tab S9").brand("Samsung").price(new BigDecimal("21990000"))
                .shortDesc("Máy tính bảng Android mạnh mẽ").detailDesc("Mô tả chi tiết...").imageUrl("tabs9.jpg")
                .category(categoryMap.get("Máy tính bảng")).build(),

            // Màn hình
            Product.builder().name("LG UltraFine 4K").brand("LG").price(new BigDecimal("14990000"))
                .shortDesc("Màn hình 4K cho dân thiết kế").detailDesc("Mô tả chi tiết...").imageUrl("lg4k.jpg")
                .category(categoryMap.get("Màn hình")).build(),

            Product.builder().name("BenQ Zowie XL2546").brand("BenQ").price(new BigDecimal("8990000"))
                .shortDesc("Màn hình gaming 240Hz").detailDesc("Mô tả chi tiết...").imageUrl("zowie.jpg")
                .category(categoryMap.get("Màn hình")).build(),

            // Bàn phím
            Product.builder().name("Logitech G Pro X").brand("Logitech").price(new BigDecimal("2990000"))
                .shortDesc("Bàn phím cơ gaming").detailDesc("Mô tả chi tiết...").imageUrl("gprox.jpg")
                .category(categoryMap.get("Bàn phím")).build(),

            Product.builder().name("Keychron K8").brand("Keychron").price(new BigDecimal("2190000"))
                .shortDesc("Bàn phím cơ không dây").detailDesc("Mô tả chi tiết...").imageUrl("keychronk8.jpg")
                .category(categoryMap.get("Bàn phím")).build(),

            // Tai nghe
            Product.builder().name("Sony WH-1000XM5").brand("Sony").price(new BigDecimal("7990000"))
                .shortDesc("Tai nghe chống ồn cao cấp").detailDesc("Mô tả chi tiết...").imageUrl("xm5.jpg")
                .category(categoryMap.get("Tai nghe")).build(),

            Product.builder().name("AirPods Pro 2").brand("Apple").price(new BigDecimal("5990000"))
                .shortDesc("Tai nghe không dây chống ồn").detailDesc("Mô tả chi tiết...").imageUrl("airpodspro2.jpg")
                .category(categoryMap.get("Tai nghe")).build(),

            // Máy ảnh
            Product.builder().name("Canon EOS R6").brand("Canon").price(new BigDecimal("52990000"))
                .shortDesc("Máy ảnh Full-frame mirrorless").detailDesc("Mô tả chi tiết...").imageUrl("canoneosr6.jpg")
                .category(categoryMap.get("Máy ảnh")).build(),

            Product.builder().name("Sony A7 IV").brand("Sony").price(new BigDecimal("48990000"))
                .shortDesc("Máy ảnh Full-frame dành cho chuyên nghiệp").detailDesc("Mô tả chi tiết...").imageUrl("sonya7iv.jpg")
                .category(categoryMap.get("Máy ảnh")).build()
        );

        for (Product product : products) {
            product.setStockQuantity(50);
            product.setSoldQuantity(random.nextInt(51)); // Random từ 0 đến 50
            product.setCreatedAt(generateRandomDate()); // Random ngày trong 6 tháng gần đây
            productRepository.save(product);
        }

        System.out.println("Đã thêm 20 sản phẩm vào DB với soldQuantity và createdAt ngẫu nhiên!");
    }

    /**
     * Sinh ngày ngẫu nhiên trong khoảng 6 tháng gần đây
     */
    private LocalDate generateRandomDate() {
        LocalDate today = LocalDate.now();
        int randomDays = random.nextInt((int) ChronoUnit.DAYS.between(today.minusMonths(6), today) + 1);
        return today.minusDays(randomDays);
    }
}
