package ru.stocktaking.stocktaking.controller.furniture;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.model.furniture.Furniture;
import ru.stocktaking.stocktaking.model.furniture.dto.FurnitureDTO;
import ru.stocktaking.stocktaking.service.building.CabinetService;
import ru.stocktaking.stocktaking.service.furniture.FurnitureService;

import java.util.List;
import java.util.Optional;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Controller
@RequestMapping("furniture")
public class FurnitureController {

    private final FurnitureService furnitureService;
    private final CabinetService cabinetService;

    public FurnitureController(FurnitureService furnitureService, CabinetService cabinetService) {
        this.furnitureService = furnitureService;
        this.cabinetService = cabinetService;
    }


    @GetMapping("cabinet/{cabinetId}")
    public String getTechByCabinetId(@PathVariable("cabinetId") int cabinetId,
                                     Model model) {
        List<Furniture> furnitureList = furnitureService.findByCabinetId(cabinetId);

        model.addAttribute("furnitureList", furnitureList);
        return "furniture/furniture_in_cabinet";
    }

    @GetMapping("{furnitureId}")
    public String getInfoByTech(@PathVariable("furnitureId") int furnitureId,
                                Model model) {
        Optional<FurnitureDTO> furniture = furnitureService.findById(furnitureId);
        List<Cabinet> cabinetList = cabinetService.findCabinetByBuilding(furniture.get().getBuilding());

        model.addAttribute("furniture", furniture.get());
        model.addAttribute("cabinetList", cabinetList);

        return "furniture/furniture_info";
    }

    @PostMapping("{furniture_id}")
    public String deleteFurniture(@PathVariable("furniture_id") int furnitureId) {
        furnitureService.deleteFurniture(furnitureId);
        return "redirect:/furniture/" + furnitureId;
    }

    @PostMapping("life/{furniture_id}")
    public String lifeFurniture(@PathVariable("furniture_id") int furnitureId) {
        furnitureService.lifeFurniture(furnitureId);
        return "redirect:/furniture/" + furnitureId;
    }

    @PostMapping("{furniture_id}/edit/cabinet/{cabinet_id}")
    public String editCabinet(@PathVariable("furniture_id") int furnitureId,
                              @PathVariable("cabinet_id") int cabinetId) {

        furnitureService.editCabinet(furnitureId, cabinetId);
        return "redirect:/furniture/" + furnitureId;
    }
}
