package co.edu.sena.ferregestion.repository;

import co.edu.sena.ferregestion.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
