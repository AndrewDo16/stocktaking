package ru.stocktaking.stocktaking.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.stocktaking.stocktaking.model.employee.Employee;

import java.util.List;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e JOIN e.cabinet c JOIN c.building b WHERE b.id = :buildingId")
    List<Employee> findEmployeesInBuilding(@Param("buildingId") int buildingId);
}
