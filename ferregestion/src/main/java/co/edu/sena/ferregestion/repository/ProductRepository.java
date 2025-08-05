package co.edu.sena.ferregestion.repository;

import co.edu.sena.ferregestion.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
