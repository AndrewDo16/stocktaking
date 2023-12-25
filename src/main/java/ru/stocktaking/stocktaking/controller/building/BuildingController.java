package ru.stocktaking.stocktaking.controller.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.stocktaking.stocktaking.model.building.Building;
import ru.stocktaking.stocktaking.model.building.dto.BuildingDTO;
import ru.stocktaking.stocktaking.repository.building.BuildingRepository;
import ru.stocktaking.stocktaking.service.building.BuildingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Controller
@RequestMapping("/building")
public class BuildingController {

    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<Building> buildingList = buildingService.findAllBuilding();
        List<BuildingDTO> buildingDTOList = new ArrayList<>();
        for (Building building : buildingList) {
            long totalCabinets = buildingService.getTotalCabinetsForBuilding(building);
            long totalTech = buildingService.getTotalTechForBuilding(building);
            long totalFurniture = buildingService.getTotalFurnitureForBuilding(building);
            BuildingDTO buildingDTO = new BuildingDTO();
            buildingDTO.setBuilding(building);
            buildingDTO.setTotalCabinets(totalCabinets);
            buildingDTO.setTotalTech(totalTech);
            buildingDTO.setTotalFurniture(totalFurniture);
            buildingDTOList.add(buildingDTO);
        }
        model.addAttribute("building", buildingDTOList);
        return "building/get_all";
    }

    @ResponseBody
    @GetMapping("all")
    public ResponseEntity<List<Building>> getAllBuilding() {
        return new ResponseEntity<>(buildingService.findAllBuilding(), HttpStatus.OK);
    }

}
