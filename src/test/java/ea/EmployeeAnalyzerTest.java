package ea;

import model.Employee;
import service.EmployeeAnalyzer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeAnalyzerTest {
    @Test
    void testSalaryComparision() {
        List<Employee> employees = Arrays.asList(
            new Employee("1", "Manager", "One", 90000, ""),
            new Employee("2", "Employee", "Two", 50000, "1")
        );
        EmployeeAnalyzer analyzer = new EmployeeAnalyzer(employees);
        assertDoesNotThrow(analyzer::printSalaryViolations);
    }

    @Test
    void testReporting() {
        List<Employee> employees = Arrays.asList(
            new Employee("1", "CEO", "", 150000, ""),
            new Employee("2", "Manager", "A", 90000, "1"),
            new Employee("3", "Manager", "B", 80000, "2"),
            new Employee("4", "Manager", "C", 70000, "3"),
            new Employee("5", "Manager", "D", 60000, "4"),
            new Employee("6", "Employee", "E", 50000, "5")
        );
        EmployeeAnalyzer analyzer = new EmployeeAnalyzer(employees);
        assertDoesNotThrow(analyzer::printLongReportingLines);
    }
}