package co.edu.sena.ferregestion.login.repository;

import co.edu.sena.ferregestion.login.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, String> {
    Optional<UserAuth> findByEmail(String email);
}