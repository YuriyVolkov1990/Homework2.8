package homework.homework2_8.service;

import homework.homework2_8.exception.EmployeeAlreadyAddedException;
import homework.homework2_8.exception.EmployeeNotFoundException;
import homework.homework2_8.exception.EmployeeStorageIsFullException;
import homework.homework2_8.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    private static final int size_limit = 5;
    private final Map<String, Employee> employees = new HashMap<>(size_limit);
    public Collection<Employee> getAll() {
        return employees.values();
    }
    public Employee add(Employee employee) {
        if (employees.size()>=size_limit) {
            throw new EmployeeStorageIsFullException();
        }
        if (employees.containsKey(createKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(employee), employee);
        return employee;
    }
    public Employee find(Employee employee) {
        employee = employees.get(createKey(employee).toLowerCase());
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }
    public Employee remove (Employee employee) {
        employee = employees.get(createKey(employee).toLowerCase());
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(createKey(employee).toLowerCase());
    }
    public static String createKey(Employee employee) {
        return (employee.getFirstName() + employee.getLastName()).toLowerCase();
    }
}
