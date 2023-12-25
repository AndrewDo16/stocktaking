package ru.stocktaking.stocktaking.controller.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.model.building.dto.CabinetDTO;
import ru.stocktaking.stocktaking.service.tech.TechService;
import ru.stocktaking.stocktaking.service.building.BuildingService;
import ru.stocktaking.stocktaking.service.building.CabinetService;

import java.util.ArrayList;
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
            long totalFurniture = cabinetService.getTotalFurnitureForCabinet(cabinet);
            CabinetDTO cabinetDTO = new CabinetDTO();
            cabinetDTO.setCabinet(cabinet);
            cabinetDTO.setTotalTech(totalTech);
            cabinetDTO.setTotalFurniture(totalFurniture);
            cabinetDTOList.add(cabinetDTO);
        }

        model.addAttribute("cabinets", cabinetDTOList);
        return "building/one_cabinet";
    }

    @GetMapping("/cabinet/{cabinetId}")
    public String showCabinetAttribute(@PathVariable("cabinetId") int cabinetId,
                                       Model model) {
        Optional<Cabinet> cabinet = cabinetService.findById(cabinetId);
        boolean hasTechInCabinet = cabinetService.hasTechInCabinet(cabinetId);
        boolean hasFurnitureInCabinet = cabinetService.hasFurnitureInCabinet(cabinetId);
        long totalTech = cabinetService.getTotalTechForCabinet(cabinet.get());
        long totalFurniture = cabinetService.getTotalFurnitureForCabinet(cabinet.get());
        model.addAttribute("cabinet", cabinet.get());
        model.addAttribute("totalTech", totalTech);
        model.addAttribute("totalFurniture", totalFurniture);
        model.addAttribute("hasTechInCabinet", hasTechInCabinet);
        model.addAttribute("hasFurnitureInCabinet", hasFurnitureInCabinet);

        return "building/cabinet_attribute";
    }

}
