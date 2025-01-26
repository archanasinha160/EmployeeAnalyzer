package service;

import model.Employee;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeAnalyzer {
    private List<Employee> employees;

    public EmployeeAnalyzer(List<Employee> employees) {
        this.employees = employees;
    }

    public void printSalaryViolations() {
        for (Employee manager : employees) {
            List<Employee> subordinates = employees.stream()
                    .filter(e -> e.getManagerId().equals(manager.getId()))
                    .collect(Collectors.toList());
            if (subordinates.isEmpty()) continue;
            double avgSubSalary = subordinates.stream().mapToDouble(Employee::getSalary).average().orElse(0);
            double minSalary = avgSubSalary * 1.2;
            double maxSalary = avgSubSalary * 1.5;
            if (manager.getSalary() < minSalary) {
                System.out.println(manager.getFirstName() + " " + manager.getLastName() + " earns too little: " + (minSalary - manager.getSalary()));
            }
            if (manager.getSalary() > maxSalary) {
                System.out.println(manager.getFirstName() + " " + manager.getLastName() + " earns too much: " + (manager.getSalary() - maxSalary));
            }
        }
    }

    public void printLongReportingLines() {
        Map<String, Integer> reportingDepth = new HashMap<>();
        for (Employee e : employees) {
            int depth = getReportingDepth(e, employees, reportingDepth);
            if (depth > 4) {
                System.out.println(e.getFirstName() + " " + e.getLastName() + " has too long reporting line: " + (depth - 4));
            }
        }
    }

    private int getReportingDepth(Employee e, List<Employee> employees, Map<String, Integer> memo) {
        if (e.getManagerId().isEmpty()) return 0;
        if (memo.containsKey(e.getId())) return memo.get(e.getId());
        Optional<Employee> manager = employees.stream().filter(emp -> emp.getId().equals(e.getManagerId())).findFirst();
        int depth = manager.map(value -> 1 + getReportingDepth(value, employees, memo)).orElse(0);
        memo.put(e.getId(), depth);
        return depth;
    }
}
