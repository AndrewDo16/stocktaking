package ru.stocktaking.stocktaking.controller.tech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stocktaking.stocktaking.service.tech.TechService;
import ru.stocktaking.stocktaking.service.building.CabinetService;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Controller
@RequestMapping("tech")
public class TechController {

    private final TechService techService;
    private final CabinetService cabinetService;

    @Autowired
    public TechController(TechService techService, CabinetService cabinetService) {
        this.techService = techService;
        this.cabinetService = cabinetService;
    }

    @GetMapping("{cabinetId}")
    public String getTechByCabinetId(@PathVariable("cabinetId") int cabinetId,
                                     Model model) {
        return "tech/tech_by_cabinet";
    }
}
