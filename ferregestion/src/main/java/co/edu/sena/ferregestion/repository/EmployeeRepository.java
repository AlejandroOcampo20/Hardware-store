package co.edu.sena.ferregestion.repository;

import co.edu.sena.ferregestion.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
