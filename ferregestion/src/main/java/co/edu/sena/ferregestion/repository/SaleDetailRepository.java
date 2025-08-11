package co.edu.sena.ferregestion.repository;

import co.edu.sena.ferregestion.model.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Integer> {
    List<SaleDetail> findBySaleId(Integer saleId);
    List<SaleDetail> findByProductId(Integer productId);
}