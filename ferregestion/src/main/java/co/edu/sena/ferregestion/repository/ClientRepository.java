package co.edu.sena.ferregestion.repository;

import co.edu.sena.ferregestion.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
