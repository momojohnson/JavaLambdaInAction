package com.momo;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
	// Java 8 lambda in action

        // Find the sum, max, min, average from an array int array using lambda
        int [] arrayOfInts = {20, 24, 15, 10, 44, 85, 50, 35, 60, 18, 100};
        IntSummaryStatistics statistics = IntStream.of(arrayOfInts)
                .summaryStatistics();
        Long sum = statistics.getSum();
        double average = statistics.getAverage();
        int max = statistics.getMax();
        int min = statistics.getMin();
        System.out.printf("Sum: %d average: %.2f min: %d max: %d%n", sum, average, min, max);

        // Find and prints all even number in the arrayOfInts in sorted order
        IntPredicate even = evenNumber -> evenNumber %2 == 0;
        System.out.printf("Sorted even numbers: ");
        IntStream.of(arrayOfInts).filter(even)
                .sorted()

                .forEach(value -> System.out.printf("%d ", value));

        // Find and print all number that aren't even number from the arrayOfInts in sorted order
        System.out.printf("%nSorted uneven numbers ");
        IntStream.of(arrayOfInts).filter(even.negate())
                .sorted()
                .forEach(value -> System.out.printf("%d ", value));


        //Processing of Employee object using Java 8 lambda

        Employee[] employees = {new Employee("Daniel", "Jenson", 6000, "IT"),
                                            new Employee("Samuel", "Johnson", 5674, "Marketing"),
                                            new Employee("Philips", "Smith", 5678.90, "Marketing"),
                                            new Employee("Melvin", "Gray", 4563, "IT"),
                                            new Employee("Joseph", "Johnson",5678, "Human Resource"),
                                            new Employee("James", "Adams", 7000, "Human Resource"),
                                            new Employee("James", "Adams", 4000, "IT"),
                                            new Employee("Samuel", "Johnson", 5673, "Marketing"),
                                            new Employee("Thomas", "Smith", 4523, "Sales"),
                                            new Employee("Thomas", "Smith", 5000, "Sales"),
                                            new Employee("James", "Gray", 5600, "IT"),
                                            new Employee("Isaiah", "Edwards", 4555.34, "Sales"),
                                            new Employee("Richard", "Edwards", 6450, "Sales"),
                                            new Employee("Jeremiah", "Houston", 3400, "Marketing")};
        //Display all employees
        System.out.println();
        System.out.println();
        System.out.printf("%-15s%-15s%-8s%-8s%n", "First name", "Last name", "Salary", "Department");
        Arrays.stream(employees)
                .forEach(System.out::println);


        //Display Employee by sorting employees by first name
        System.out.println("\nPrint Employees with first name sorted");
        System.out.printf("%-15s%-15s%-8s%-8s%n", "First name", "Last name", "Salary", "Department");
        Arrays.stream(employees)
                .sorted(Comparator.comparing(Employee::getFirstName))
                .forEach(System.out::println);

        // Print employees, sort first name and then sort the last name


        System.out.println("\nPrint Employees sorting the first name then the last name");
        System.out.printf("%-15s%-15s%-8s%-8s%n", "First name", "Last name", "Salary", "Department");

        Function<Employee, String> firstName = Employee::getFirstName;
        Function<Employee, String> lastName = Employee::getLastName;

        Comparator<Employee> sortByFirstNameThenLastName = Comparator.comparing(firstName).thenComparing(lastName);

        Arrays.stream(employees)
                .sorted(sortByFirstNameThenLastName)
                .forEach(System.out::println);

        // Print employees that make a salary between 4000 - 6000. Sort the employees using salary
        System.out.println("\nPrint Employees with salary between 4000-6000. Sort employees using their salary");
        System.out.printf("%-15s%-15s%-8s%-8s%n", "First name", "Last name", "Salary", "Department");
        Predicate<Employee> salaryBtwFourThousandAndSixThousand = employee -> employee.getSalary() >= 4500 && employee.getSalary() <= 6000;
        Arrays.stream(employees)
                .filter(salaryBtwFourThousandAndSixThousand)
                .sorted(Comparator.comparing(Employee::getSalary))
                .forEach(System.out::println);

        System.out.println("\nPrint first employee with salary between 4000-6000 inclusively.");
        System.out.printf("%-15s%-15s%-8s%-8s%n", "First name", "Last name", "Salary", "Department");
        System.out.printf("%s%n", Arrays.stream(employees).filter(salaryBtwFourThousandAndSixThousand).findFirst().get());

        // Display unique employee first name in sorted order
        System.out.println("\nPrint employee unique first name in sorted order");

        Arrays.stream(employees)
                .map(Employee::getFirstName)
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.println("\nDisplaying employees base on department");
        // Grouping employees by department
        Map<String, List<Employee>> groupEmployeesByDepartment = Arrays.asList(employees)
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        groupEmployeesByDepartment.forEach((department, employee)->{
            System.out.printf("%n%s: %s%n", "Department", department);
            employee.forEach(System.out::println);
        });

        System.out.println("Counting employees base on employee department");

        Map<String, Long> countingEmployeesPerDepartment = Arrays.asList(employees)
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        countingEmployeesPerDepartment.forEach((department, employeeCount)->{
            System.out.printf("%n%s%nEmployee count: %d%n", department, employeeCount);
        });

        // Display the sum of all employees salary
        double employeeSalarySum = Arrays.stream(employees)
                .mapToDouble(Employee::getSalary).sum();

        //Employees Salary sum using reduce
        double employeeSumTwo = Arrays.stream(employees)
                .mapToDouble(Employee::getSalary)
                .reduce(0, (value1, value2)-> value1 + value2);

        // Calculate employees average salary
        double averageSalary = Arrays.stream(employees)
                .mapToDouble(Employee::getSalary).average().getAsDouble();

        // Get employee maximum salary
        double maxSalary = Arrays.stream(employees)
                .mapToDouble(Employee::getSalary).max().getAsDouble();

        //Summary statistics for employees
        DoubleSummaryStatistics employeeSummaryStats= Arrays.stream(employees).mapToDouble(Employee::getSalary).summaryStatistics();


        // Get Employee min salary
        double minSalary = Arrays.stream(employees).mapToDouble(Employee::getSalary).min().getAsDouble();

        System.out.printf("%nEmployees salary sum: %.2f%nEmployees Salary Average: %.2f%nEmployees max salary: %.2f%nEmployees min salary: %s%n,",
                employeeSalarySum, averageSalary, maxSalary, minSalary);





    }
}


