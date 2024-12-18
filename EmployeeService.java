package employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class EmployeeService {

    private HashSet<Employee> empset = new HashSet<>();

    private Employee emp1 = new Employee(101, "Yaja", 24, "Developer", "IT", 25000);
    private Employee emp2 = new Employee(102, "Dio", 26, "Tester", "CO", 57000);
    private Employee emp3 = new Employee(103, "Arsyad", 30, "Admin", "CO", 5000);
    private Employee emp4 = new Employee(104, "Bayu", 27, "Engineer", "CO", 70000);
    private Employee emp5 = new Employee(105, "Nando", 29, "Maintanance", "CO", 75000);

    private Scanner sc = new Scanner(System.in);

    public EmployeeService() {
        empset.add(emp1);
        empset.add(emp2);
        empset.add(emp3);
        empset.add(emp4);
        empset.add(emp5);
    }

    public void viewAllEmps(String sortBy) {
    	Scanner scanner = new Scanner(System.in);
        if (empset.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }

        List<Employee> empList = new ArrayList<Employee>(empset);

        System.out.println("Choose the field to sort employees:");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Age");
        System.out.println("4. Salary");
        System.out.println("5. Department");
        System.out.print("Enter your choice (1-5): ");
        
        int choice = scanner.nextInt();
        String sortBy1 = "";

        switch (choice) {
            case 1:
                sortBy1 = "id";
                empList.sort(Comparator.comparingInt(Employee::getId));
                break;
            case 2:
                sortBy1 = "name";
                empList.sort(Comparator.comparing(Employee::getName));
                break;
            case 3:
                sortBy1 = "age";
                empList.sort(Comparator.comparingInt(Employee::getAge));
                break;
            case 4:
                sortBy1 = "salary";
                empList.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
                break;
            case 5:
                sortBy1 = "department";
                empList.sort(Comparator.comparing(Employee::getDepartment));
                break;
            default:
                System.out.println("Invalid choice! Defaulting to sorting by ID.");
                sortBy1 = "id";
                break;
        }


        System.out.println("\n--- All Employees (Sorted by " + sortBy1 + ") ---");
        for (Employee emp : empList) {
            System.out.println(emp);
        }
    }

    public void viewEmp() {
        System.out.print("Enter Employee ID to view: ");
        int id = sc.nextInt();
        boolean found = false;

        for (Employee emp : empset) {
            if (emp.getId() == id) {
                System.out.println("\nEmployee Details:");
                System.out.println(emp);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee with ID " + id + " is not present.");
        }
    }

    public void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        boolean found = false;

        for (Employee emp : empset) {
            if (emp.getId() == id) {
                System.out.print("Enter new name: ");
                String name = sc.next();
                System.out.print("Enter new salary: ");
                double sal = sc.nextDouble();

                emp.setName(name);
                emp.setSalary(sal);

                System.out.println("\nUpdated Employee Details:");
                System.out.println(emp);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee with ID " + id + " is not present.");
        } else {
            System.out.println("Employee details updated successfully!");
        }
    }

    public void deleteEmp() {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        boolean found = false;
        Employee empToDelete = null;

        for (Employee emp : empset) {
            if (emp.getId() == id) {
                empToDelete = emp;
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee with ID " + id + " is not present.");
        } else {
            empset.remove(empToDelete);
            System.out.println("Employee deleted successfully!");
        }
    }

    public void addEmp() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        System.out.print("Enter name: ");
        String name = sc.next();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        System.out.print("Enter designation: ");
        String designation = sc.next();
        System.out.print("Enter department: ");
        String department = sc.next();
        System.out.print("Enter salary: ");
        double sal = sc.nextDouble();

        Employee newEmp = new Employee(id, name, age, designation, department, sal);

        if (empset.add(newEmp)) {
            System.out.println("\nNew Employee Added Successfully:");
            System.out.println(newEmp);
        } else {
            System.out.println("Employee with ID " + id + " already exists.");
        }
    }
    



}
