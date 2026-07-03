package Day4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
    public static void main(String[] args) {
    	List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "IT", 55000));
        employees.add(new Employee("Bob", "Finance", 60000));
        employees.add(new Employee("Alice", "HR", 52000)); // duplicate name
        employees.add(new Employee("Ken", "IT", 60000));
        employees.add(new Employee("Maria", "HR", 50000));
        employees.add(new Employee("John", "Finance", 70000));
        employees.add(new Employee("Ken", "Finance", 65000)); // duplicate name
        employees.add(new Employee("Lara", "IT", 62000));
        employees.add(new Employee("Sam", "HR", 48000));
        employees.add(new Employee("Bob", "IT", 59000)); // duplicate name
        
        printUniqueEmployees(employees);
        printEmployeesDepartment(employees);
        printHighestPaid(employees);
        printBySalary(employees);
        printUniqueSalaries(employees);
	}
	
	public static void printUniqueEmployees(List<Employee> employees) {
		Set<String> names = new HashSet<>();
		for (Employee employee : employees) {
			names.add(employee.getName());
		}
		System.out.println("Unique employee names:");
		System.out.println(names.toString());
	}
	
	public static void printEmployeesDepartment(List<Employee> employees) {
		Map<String, List<Employee>> departments = new HashMap<>();
		
		for (Employee employee : employees) {
			if (!departments.containsKey(employee.getDepartment()) ) {
				departments.put(employee.getDepartment(), new ArrayList<Employee>());
			}
			departments.get(employee.getDepartment()).add(employee);
		}
		
		System.out.println("Employee departments:");
		
		for (Map.Entry<String, List<Employee>> department : departments.entrySet()) {
			System.out.println(department.getKey() + ":");
			for (Employee employee : department.getValue()) {
				System.out.println(" - " + employee.toString());
			}
			System.out.println();
		}
	}
	
	public static void printHighestPaid(List<Employee> employees) {
		Map<String, List<Employee>> departments = new HashMap<>();
		
		for (Employee employee : employees) {
			if (!departments.containsKey(employee.getDepartment()) ) {
				departments.put(employee.getDepartment(), new ArrayList<Employee>());
			}
			departments.get(employee.getDepartment()).add(employee);
		}
		
		System.out.println("Highest paid per department:");
		
		for (Map.Entry<String, List<Employee>> department : departments.entrySet()) {
			System.out.print("Highest paid for " + department.getKey() + ": ");
			double maxSalary = 0;
			Employee topEmployee = null;
			for (Employee employee : department.getValue()) {
				if (employee.getSalary() > maxSalary) {
					maxSalary = employee.getSalary();
					topEmployee = employee;
				}
			}
			if (topEmployee != null) {
				System.out.println(topEmployee.toString());
			}
		}		
	}
	
	public static void printBySalary(List<Employee> employees) {
		employees.sort(Comparator.comparing(Employee::getSalary));
		Collections.reverse(employees);
		
		System.out.println("Salaries sorted (descending):");
		for (Employee employee : employees) {
			System.out.println(" - " + String.format("%.10f", employee.getSalary()).replaceAll("\\.?0+$", ""));
		}
	}
	
	public static void printUniqueSalaries(List<Employee> employees) {
		Set<Double> salaries = new TreeSet<>();
		
		for (Employee employee : employees) {
			salaries.add(employee.getSalary());
		}
		
		System.out.println("Unique salaries:");
		for (double salary : salaries) {
			System.out.println(" - " + String.format("%.10f", salary).replaceAll("\\.?0+$", ""));
		}
	}
}
