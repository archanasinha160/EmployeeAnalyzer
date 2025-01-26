package service;

import model.Employee;
import java.io.*;
import java.util.*;

public class CsvParser {
    public static List<Employee> loadEmployees(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 4) continue;
                String id = values[0].trim();
                String firstName = values[1].trim();
                String lastName = values[2].trim();
                double salary = Double.parseDouble(values[3].trim());
                String managerId = values.length > 4 ? values[4].trim() : "";
                employees.add(new Employee(id, firstName, lastName, salary, managerId));
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return employees;
    }
}
