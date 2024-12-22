package package_employee;
import java.util.HashSet;
import java.util.Set;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    private final Set<Employee> employees = new HashSet<>();

    public InMemoryEmployeeRepository() {
        // Pre-populate employees 
        employees.add(new Employee(101, "Yaja", 24, "Developer", "IT", 25000));
        employees.add(new Employee(102, "Dio", 26, "Tester", "CO", 57000));
        employees.add(new Employee(103, "Arsyad", 30, "Admin", "CO", 5000));
        employees.add(new Employee(104, "Bayu", 27, "Engineer", "CO", 70000));
        employees.add(new Employee(105, "Nando", 29, "Maintenance", "CO", 75000));
    }

    @Override
    public Set<Employee> findAll() {
        return new HashSet<>(employees);
    }

    @Override
    public Employee findById(int id) {
        return employees.stream().filter(emp -> emp.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean save(Employee employee) {
        return employees.add(employee);
    }

    @Override
    public boolean deleteById(int id) {
        return employees.removeIf(emp -> emp.getId() == id);
    }
}

