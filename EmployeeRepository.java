package package_employee;


import java.util.Set;

public interface EmployeeRepository {
    Set<Employee> findAll();
    Employee findById(int id);
    boolean save(Employee employee);
    boolean deleteById(int id);
}
