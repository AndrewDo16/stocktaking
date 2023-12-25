package ru.stocktaking.stocktaking.controller.tech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stocktaking.stocktaking.model.building.Building;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.model.employee.Employee;
import ru.stocktaking.stocktaking.model.tech.Tech;
import ru.stocktaking.stocktaking.model.tech.dto.TechComputerInfoDTO;
import ru.stocktaking.stocktaking.model.tech.dto.TechPrinterInfoDTO;
import ru.stocktaking.stocktaking.service.employee.EmployeeService;
import ru.stocktaking.stocktaking.service.tech.TechService;
import ru.stocktaking.stocktaking.service.building.CabinetService;

import java.util.List;
import java.util.Optional;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Controller
@RequestMapping("tech")
public class TechController {

    private final TechService techService;
    private final CabinetService cabinetService;
    private final EmployeeService employeeService;

    @Autowired
    public TechController(TechService techService, CabinetService cabinetService, EmployeeService employeeService) {
        this.techService = techService;
        this.cabinetService = cabinetService;
        this.employeeService = employeeService;
    }

    @GetMapping("cabinet/{cabinetId}")
    public String getTechByCabinetId(@PathVariable("cabinetId") int cabinetId,
                                     Model model) {
        List<Tech> techList = techService.findByCabinetId(cabinetId);

        model.addAttribute("techList", techList);
        return "tech/tech_in_cabinet";
    }

    @GetMapping("{techId}")
    public String getInfoByTech(@PathVariable("techId") int techId,
                                Model model) {

        Optional<Tech> oneTech = techService.findOne(techId);
        Building building = oneTech.get().getCabinet().getBuilding();
        List<Cabinet> cabinetList = cabinetService.findCabinetByBuilding(building);
        List<Employee> employeesList = employeeService.findEmployeesByBuilding(building.getId());

        Object tech = techService.findInfoTech(techId);
        if (tech.getClass().getSimpleName().equals("TechComputerInfoDTO")) {
            model.addAttribute("techInfo", (TechComputerInfoDTO) tech);
            model.addAttribute("cabinetList", cabinetList);
            model.addAttribute("employeesList", employeesList);
        } else {
            model.addAttribute("techInfo", (TechPrinterInfoDTO) tech);
            model.addAttribute("cabinetList", cabinetList);
            model.addAttribute("employeesList", employeesList);
        }
        return "tech/tech_info";
    }

    @PostMapping("{techId}")
    public String deleteTech(@PathVariable("techId") int techId) {
        techService.deleteTech(techId);
        return "redirect:/tech/" + techId;
    }

    @PostMapping("life/{techId}")
    public String lifeTech(@PathVariable("techId") int techId) {
        techService.lifeTech(techId);
        return "redirect:/tech/" + techId;
    }

    @PostMapping("{tech_id}/edit/cabinet/{cabinet_id}")
    public String editCabinet(@PathVariable("tech_id") int techId,
                              @PathVariable("cabinet_id") int cabinetId) {

        techService.editCabinet(techId, cabinetId);
        return "redirect:/tech/" + techId;
    }

    @PostMapping("{tech_id}/edit/employee/{employee_id}")
    public String editEmployee(@PathVariable("tech_id") int techId,
                              @PathVariable("employee_id") int employeeId) {

        techService.editEmployee(techId, employeeId);
        return "redirect:/tech/" + techId;
    }
}
