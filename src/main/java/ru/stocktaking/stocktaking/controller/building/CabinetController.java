package ru.stocktaking.stocktaking.controller.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.model.building.dto.CabinetDTO;
import ru.stocktaking.stocktaking.service.TechService;
import ru.stocktaking.stocktaking.service.building.BuildingService;
import ru.stocktaking.stocktaking.service.building.CabinetService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Controller
@RequestMapping("/building/department")
public class CabinetController {

    private final BuildingService buildingService;
    private final CabinetService cabinetService;
    private final TechService techService;

    @Autowired
    public CabinetController(BuildingService buildingService, CabinetService cabinetService, TechService techService) {
        this.buildingService = buildingService;
        this.cabinetService = cabinetService;
        this.techService = techService;
    }

    @GetMapping("/{departmentId}/cabinet")
    public String showBuildingAttribute(@PathVariable("departmentId") int departmentId,
                                        Model model) {

        List<Cabinet> cabinets = cabinetService.findCabinetByDepartmentId(departmentId);
        List<CabinetDTO> cabinetDTOList = new ArrayList<>();

        for (Cabinet cabinet : cabinets) {
            long totalTech = cabinetService.getTotalTechForCabinet(cabinet);
            CabinetDTO cabinetDTO = new CabinetDTO();
            cabinetDTO.setCabinet(cabinet);
            cabinetDTO.setTotalTech(totalTech);
            cabinetDTOList.add(cabinetDTO);
        }

        model.addAttribute("cabinets", cabinetDTOList);
        return "building/one_cabinet";
    }

}
