package ru.stocktaking.stocktaking.controller.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stocktaking.stocktaking.model.building.Department;
import ru.stocktaking.stocktaking.model.building.dto.DepartmentDTO;
import ru.stocktaking.stocktaking.service.building.BuildingService;
import ru.stocktaking.stocktaking.service.building.DepartmentService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Controller
@RequestMapping("/building/department")
public class DepartmentController {

    private final BuildingService buildingService;
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(BuildingService buildingService, DepartmentService departmentService) {
        this.buildingService = buildingService;
        this.departmentService = departmentService;
    }

    @GetMapping("/{buildingId}")
    public String showBuildingAttribute(@PathVariable("buildingId") int buildingId,
                                        Model model) {
        List<Department> departments = departmentService.findDepartmentByBuildingId(buildingId);
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();

        for (Department department : departments) {
            long totalCabinets = departmentService.getTotalCabinetsForDepartment(department);
            long totalTech = departmentService.getTotalTechForDepartment(department);
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setDepartment(department);
            departmentDTO.setTotalCabinets(totalCabinets);
            departmentDTO.setTotalTech(totalTech);
            departmentDTOList.add(departmentDTO);
        }

        model.addAttribute("departments", departmentDTOList);
        return "building/get_all_departments";
    }
}
