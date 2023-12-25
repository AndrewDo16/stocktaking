package ru.stocktaking.stocktaking.repository.building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stocktaking.stocktaking.model.building.Building;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.model.building.Department;

import java.util.List;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, Integer> {
    long countByBuilding(Building building);
    long countByDepartment(Department department);

    List<Cabinet> findByBuilding(Building building);
    List<Cabinet> findByDepartment(Department department);

    List<Cabinet> findCabinetsByDepartmentId(int departmentId);
    List<Cabinet> findCabinetsByBuilding(Building building);
}
