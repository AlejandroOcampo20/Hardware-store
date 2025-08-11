package co.edu.sena.ferregestion.repository;

import co.edu.sena.ferregestion.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findByIsActiveTrue();
    List<Supplier> findByNameContainingIgnoreCase(String name);
}