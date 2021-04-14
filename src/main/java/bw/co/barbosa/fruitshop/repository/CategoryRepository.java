package bw.co.barbosa.fruitshop.repository;

import bw.co.barbosa.fruitshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
