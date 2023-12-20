package ru.stocktaking.stocktaking.repository.building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stocktaking.stocktaking.model.building.Department;

import java.util.List;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    List<Department> findDepartmentByBuildingId(int buildingId);
}
