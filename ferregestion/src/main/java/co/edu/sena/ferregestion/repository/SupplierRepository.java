package co.edu.sena.ferregestion.repository;

import co.edu.sena.ferregestion.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
