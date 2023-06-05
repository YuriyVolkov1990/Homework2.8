package homework.homework2_8.service;

import homework.homework2_8.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public Employee getEmployeeWithMaxSalary(int depart){
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == depart)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }
    public Employee getEmployeeWithMinSalary(int depart){
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == depart)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }
    public List<Employee> getEmployeeByDepartment(int depart) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == depart)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getEmployeeMap() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
