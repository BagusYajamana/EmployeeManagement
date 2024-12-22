package package_employee;

import java.util.*;
//import java.util.stream.Collectors;

public class EmployeeService {
    private final EmployeeRepository repository;
    private final Scanner scanner = new Scanner(System.in);

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void addEmployee() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Designation: ");
        String designation = scanner.next();
        System.out.print("Enter Department: ");
        String department = scanner.next();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(id, name, age, designation, department, salary);
        if (repository.save(employee)) {
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Employee with ID already exists.");
        }
    }

    public void viewEmployee(int id) {
        Employee employee = repository.findById(id);
        if (employee != null) {
            System.out.println(employee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void viewAllEmployees() {
        List<Employee> employees = new ArrayList<>(repository.findAll());

        System.out.println("\nChoose sorting option:");
        System.out.println("1. By ID");
        System.out.println("2. By Name");
        System.out.println("3. By Age");
        System.out.println("4. By Salary (Descending)");
        System.out.println("5. By Department");
        System.out.print("Enter your choice (1-5): ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                employees.sort(Comparator.comparing(Employee::getId));
                break;
            case 2:
                employees.sort(Comparator.comparing(Employee::getName));
                break;
            case 3:
                employees.sort(Comparator.comparing(Employee::getAge));
                break;
            case 4:
                employees.sort(Comparator.comparing(Employee::getSalary).reversed());
                break;
            case 5:
                employees.sort(Comparator.comparing(Employee::getDepartment));
                break;
            default:
                System.out.println("Invalid choice! Defaulting to sorting by ID.");
                employees.sort(Comparator.comparing(Employee::getId));
        }

        if (employees.isEmpty()) {
            System.out.println("\nNo employees to display.");
            return;
        }

        System.out.println("\n--- Employee List ---");
        System.out.println("\n=======================================================================");
        System.out.println("| ID   | Name         | Age  | Designation  | Department | Salary     |");
        System.out.println("=======================================================================");

        for (Employee emp : employees) {
            System.out.printf(
                    "| %-4d | %-12s | %-4d | %-12s | %-10s | %-10.2f |\n",
                    emp.getId(),
                    emp.getName(),
                    emp.getAge(),
                    emp.getDesignation(),
                    emp.getDepartment(),
                    emp.getSalary()
            );
        }

        System.out.println("=======================================================================");
        
        //employees.forEach(System.out::println);
    }

    public void updateEmployee(int id) {
        Employee employee = repository.findById(id);

        if (employee != null) {
            System.out.println("Current details:");
            System.out.println(employee);

            System.out.println("\nEnter new details (type 'skip' to keep the current value):");

            System.out.print("Enter new Name: ");
            String name = scanner.next();
            if (!name.equalsIgnoreCase("skip")) {
                employee.setName(name);
            }

            System.out.print("Enter new Age: ");
            String ageInput = scanner.next();
            if (!ageInput.equalsIgnoreCase("skip")) {
                int age = Integer.parseInt(ageInput);
                employee.setAge(age);
            }

            System.out.print("Enter new Designation: ");
            String designation = scanner.next();
            if (!designation.equalsIgnoreCase("skip")) {
                employee.setDesignation(designation);
            }

            System.out.print("Enter new Department: ");
            String department = scanner.next();
            if (!department.equalsIgnoreCase("skip")) {
                employee.setDepartment(department);
            }

            System.out.print("Enter new Salary: ");
            String salaryInput = scanner.next();
            if (!salaryInput.equalsIgnoreCase("skip")) {
                double salary = Double.parseDouble(salaryInput);
                employee.setSalary(salary);
            }

            System.out.println("\nUpdated Employee Details:");
            System.out.println(employee);

            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }


    public void deleteEmployee(int id) {
        if (repository.deleteById(id)) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }
}
