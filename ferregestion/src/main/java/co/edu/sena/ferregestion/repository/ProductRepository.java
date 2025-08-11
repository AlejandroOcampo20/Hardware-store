package co.edu.sena.ferregestion.repository;

import co.edu.sena.ferregestion.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByIsActiveTrue();
    Optional<Product> findByCode(String code);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategory(String category);

    @Query("SELECT p FROM Product p WHERE p.stock <= p.minStock AND p.isActive = true")
    List<Product> findLowStockProducts();

    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.isActive = true")
    List<String> findDistinctCategories();
}