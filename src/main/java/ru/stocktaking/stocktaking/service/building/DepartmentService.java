package ru.stocktaking.stocktaking.service.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stocktaking.stocktaking.model.building.Building;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.model.building.Department;
import ru.stocktaking.stocktaking.repository.building.CabinetRepository;
import ru.stocktaking.stocktaking.repository.building.DepartmentRepository;
import ru.stocktaking.stocktaking.repository.furniture.FurnitureRepository;
import ru.stocktaking.stocktaking.repository.tech.TechRepository;

import java.util.List;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CabinetRepository cabinetRepository;
    private final TechRepository techRepository;
    private final FurnitureRepository furnitureRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, CabinetRepository cabinetRepository, TechRepository techRepository, FurnitureRepository furnitureRepository) {
        this.departmentRepository = departmentRepository;
        this.cabinetRepository = cabinetRepository;
        this.techRepository = techRepository;
        this.furnitureRepository = furnitureRepository;
    }

    public List<Department> findDepartmentByBuildingId(int buildingId) {
        return departmentRepository.findDepartmentByBuildingId(buildingId);
    }

    public long getTotalCabinetsForDepartment(Department department) {
        return cabinetRepository.countByDepartment(department);
    }

    public long getTotalTechForDepartment(Department department) {
        List<Cabinet> cabinets = cabinetRepository.findByDepartment(department);
        long totalTech = 0;

        for (Cabinet cabinet : cabinets) {
            totalTech += techRepository.countActiveTechByCabinet(cabinet);
        }

        return totalTech;
    }

    public long getTotalFurnitureForDepartment(Department department) {
        List<Cabinet> cabinets = cabinetRepository.findByDepartment(department);
        long totalFurniture = 0;

        for (Cabinet cabinet : cabinets) {
            totalFurniture += furnitureRepository.countActiveFurnitureByCabinet(cabinet);
        }

        return totalFurniture;
    }

}
