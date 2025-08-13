package co.edu.sena.ferregestion.repository;

import co.edu.sena.ferregestion.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByIsActiveTrue();
    List<Employee> findByIsActiveFalse();
    Optional<Employee> findByDocument(String document);
    List<Employee> findByNameContainingIgnoreCase(String name);

    Optional<Employee> findByEmail(String email);
}