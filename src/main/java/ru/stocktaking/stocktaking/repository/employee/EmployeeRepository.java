package ru.stocktaking.stocktaking.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stocktaking.stocktaking.model.employee.Employee;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
