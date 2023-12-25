package ru.stocktaking.stocktaking.controller.furniture;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stocktaking.stocktaking.model.furniture.Furniture;
import ru.stocktaking.stocktaking.model.tech.Tech;
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

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
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

        Optional<Furniture> furniture = furnitureService.findById(furnitureId);

        model.addAttribute("furniture", furniture.get());

        return "furniture/furniture_info";
    }

    @PostMapping("{furniture_id}")
    public String deleteTech(@PathVariable("furniture_id") int furnitureId) {
        furnitureService.deleteFurniture(furnitureId);
        return "redirect:/furniture/" + furnitureId;
    }
}
