package co.edu.sena.ferregestion.login.service;

import co.edu.sena.ferregestion.login.model.UserAuth;
import co.edu.sena.ferregestion.login.repository.UserAuthRepository;
import co.edu.sena.ferregestion.model.Employee;
import co.edu.sena.ferregestion.repository.EmployeeRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeUserDetailsService(UserAuthRepository userAuthRepository,
                                      EmployeeRepository employeeRepository) {
        this.userAuthRepository = userAuthRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!userAuth.getIsActive()) {
            throw new UsernameNotFoundException("Usuario inactivo");
        }

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado"));

        if (!employee.getActive()) {
            throw new UsernameNotFoundException("Empleado inactivo");
        }

        return new EmployeeUserDetails(userAuth, employee);
    }

    public static class EmployeeUserDetails implements UserDetails {
        private final UserAuth userAuth;
        private final Employee employee;

        public EmployeeUserDetails(UserAuth userAuth, Employee employee) {
            this.userAuth = userAuth;
            this.employee = employee;
        }

        @Override
        public String getUsername() {
            return userAuth.getEmail();
        }

        @Override
        public String getPassword() {
            return userAuth.getPassword();
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_" + userAuth.getRole())
            );
        }

        @Override
        public boolean isAccountNonExpired() { return true; }

        @Override
        public boolean isAccountNonLocked() { return true; }

        @Override
        public boolean isCredentialsNonExpired() { return true; }

        @Override
        public boolean isEnabled() {
            return userAuth.getIsActive() && employee.getActive();
        }

        public Employee getEmployee() {
            return employee;
        }
    }
}