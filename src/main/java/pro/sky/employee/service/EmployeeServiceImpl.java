package pro.sky.employee.service;

import org.springframework.stereotype.Service;
import pro.sky.employee.exception.EmployeeAlreadyAddedException;
import pro.sky.employee.exception.EmployeeNotFoundException;
import pro.sky.employee.model.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {

        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFulName())){
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFulName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFulName())) {
            return employees.remove(employee.getFulName());

        }

       throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employees.containsKey(employee.getFulName())) {
            return employees.get(employee.getFulName());
        }

        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
