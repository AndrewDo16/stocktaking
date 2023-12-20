package ru.stocktaking.stocktaking.controller.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stocktaking.stocktaking.model.building.Building;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.service.building.BuildingService;
import ru.stocktaking.stocktaking.service.building.CabinetService;

import java.util.List;
import java.util.Optional;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Controller
@RequestMapping("/building/department")
public class CabinetController {

    private final BuildingService buildingService;
    private final CabinetService cabinetService;

    @Autowired
    public CabinetController(BuildingService buildingService, CabinetService cabinetService) {
        this.buildingService = buildingService;
        this.cabinetService = cabinetService;
    }

    @GetMapping("/{departmentId}/cabinet")
    public String showBuildingAttribute(@PathVariable("departmentId") int departmentId,
                                        Model model) {

        List<Cabinet> cabinets = cabinetService.findCabinetByDepartmentId(departmentId);
        model.addAttribute("cabinets", cabinets);
        return "building/one_cabinet";
    }

}
