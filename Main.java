package package_employee;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeRepository repository = new InMemoryEmployeeRepository();
        EmployeeService service = new EmployeeService(repository);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. View All Employees");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    service.addEmployee();
                    break;
                case 2:
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    service.viewEmployee(id);
                    break;
                case 3:
                    service.viewAllEmployees();
                    break;
                case 4:
                    System.out.print("Enter Employee ID: ");
                    id = scanner.nextInt();
                    service.updateEmployee(id);
                    break;
                case 5:
                    System.out.print("Enter Employee ID: ");
                    id = scanner.nextInt();
                    service.deleteEmployee(id);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
