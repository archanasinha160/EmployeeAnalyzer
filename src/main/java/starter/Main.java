package starter;

import service.CsvParser;
import service.EmployeeAnalyzer;
import java.util.List;

import model.Employee;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/employees.csv";
        List<Employee> employees = CsvParser.loadEmployees(filePath);
        EmployeeAnalyzer analyzer = new EmployeeAnalyzer(employees);
        
        analyzer.printSalaryViolations();
        analyzer.printLongReportingLines();
    }
}