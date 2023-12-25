package ru.stocktaking.stocktaking.service.employee;

import org.springframework.stereotype.Service;
import ru.stocktaking.stocktaking.model.building.Building;
import ru.stocktaking.stocktaking.model.employee.Employee;
import ru.stocktaking.stocktaking.repository.employee.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndreyDo16 on 25.12.2023
 */

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findEmployeesByBuilding(int buildingId) {
        return employeeRepository.findEmployeesInBuilding(buildingId);
    }
}
