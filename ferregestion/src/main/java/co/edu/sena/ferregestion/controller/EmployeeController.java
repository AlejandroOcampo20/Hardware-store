package co.edu.sena.ferregestion.controller;

import co.edu.sena.ferregestion.model.Employee;
import co.edu.sena.ferregestion.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")

public class EmployeeController {

        @Autowired
        private EmployeeRepository employeeRepository;

        @GetMapping
        public List<Employee> getAll() {
            return employeeRepository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Employee> getById(@PathVariable Long id) {
            return employeeRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public Employee create(@RequestBody Employee employee) {
            return employeeRepository.save(employee);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
            return employeeRepository.findById(id)
                    .map(employee -> {
                        updatedEmployee.setId(id);
                        return ResponseEntity.ok(employeeRepository.save(updatedEmployee));
                    }).orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            employeeRepository.deleteById(id);
        }

}
