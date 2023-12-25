package ru.stocktaking.stocktaking.service.tech;

import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.model.employee.Employee;
import ru.stocktaking.stocktaking.model.furniture.Furniture;
import ru.stocktaking.stocktaking.model.tech.Computer;
import ru.stocktaking.stocktaking.model.tech.Printer;
import ru.stocktaking.stocktaking.model.tech.Tech;
import ru.stocktaking.stocktaking.model.tech.dto.TechComputerInfoDTO;
import ru.stocktaking.stocktaking.model.tech.dto.TechPrinterInfoDTO;
import ru.stocktaking.stocktaking.repository.building.CabinetRepository;
import ru.stocktaking.stocktaking.repository.employee.EmployeeRepository;
import ru.stocktaking.stocktaking.repository.tech.ComputerRepository;
import ru.stocktaking.stocktaking.repository.tech.PrinterRepository;
import ru.stocktaking.stocktaking.repository.tech.TechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stocktaking.stocktaking.service.building.CabinetService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Service
public class TechService {

    private final TechRepository techRepository;
    private final ComputerRepository computerRepository;
    private final PrinterRepository printerRepository;
    private final CabinetRepository cabinetRepository;
    private final EmployeeRepository employeeRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @Autowired
    public TechService(TechRepository techRepository, ComputerRepository computerRepository, PrinterRepository printerRepository, CabinetRepository cabinetRepository, EmployeeRepository employeeRepository) {
        this.techRepository = techRepository;
        this.computerRepository = computerRepository;
        this.printerRepository = printerRepository;
        this.cabinetRepository = cabinetRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Tech> findByCabinetId(int cabinetId) {
        return techRepository.findTechByCabinetId(cabinetId);
    }

    public Object findInfoTech(int techId) {

        Optional<Tech> tech = techRepository.findById(techId);
        Optional<Computer> computer = computerRepository.findComputerByTechId(techId);
        Optional<Printer> printer = printerRepository.findPrinterByTechId(techId);
        if (computer.isPresent()) {
            return convertTechWithComputerToTechInfoDTO(tech, computer);
        }
        if (printer.isPresent()) {
            return convertTechWithPrinterToTechInfoDTO(tech, printer);
        }

        return null;
    }

    public Optional<Tech> findOne(int techId) {
        return techRepository.findById(techId);
    }

    private TechPrinterInfoDTO convertTechWithPrinterToTechInfoDTO(Optional<Tech> tech, Optional<Printer> printer) {
        LocalDateTime derigistrationDate = tech.get().getDerigistrationDate();

        return TechPrinterInfoDTO.builder()
                .id(tech.get().getId())
                .cabinetId(tech.get().getCabinet().getId())
                .cabinetNumber(tech.get().getCabinet().getCabinetNumber())
                .employeeId(tech.get().getEmployee().getId())
                .employeeName(tech.get().getEmployee().getName() + " " + tech.get().getEmployee().getSurname())
                .serialNumber(tech.get().getSerialNumber())
                .inventoryNumber(tech.get().getInventoryNumber())
                .type(tech.get().getType())
                .model(tech.get().getModel())
                .isActive(tech.get().isActive())
                .registrationDate(tech.get().getRegistrationDate().format(formatter))
                .derigistrationDate(derigistrationDate == null ? "null" : derigistrationDate.format(formatter))
                .producer(printer.get().getProducer())
                .printType(printer.get().getPrintType())
                .printTechnology(printer.get().getPrintTechnology())
                .printSpeed(printer.get().getPrintSpeed())
                .build();
    }

    private TechComputerInfoDTO convertTechWithComputerToTechInfoDTO(Optional<Tech> tech, Optional<Computer> computer) {
        LocalDateTime derigistrationDate = tech.get().getDerigistrationDate();

        return TechComputerInfoDTO.builder()
                .id(tech.get().getId())
                .cabinetId(tech.get().getCabinet().getId())
                .cabinetNumber(tech.get().getCabinet().getCabinetNumber())
                .employeeId(tech.get().getEmployee().getId())
                .employeeName(tech.get().getEmployee().getName() + " " + tech.get().getEmployee().getSurname())
                .serialNumber(tech.get().getSerialNumber())
                .inventoryNumber(tech.get().getInventoryNumber())
                .type(tech.get().getType())
                .model(tech.get().getModel())
                .isActive(tech.get().isActive())
                .registrationDate(tech.get().getRegistrationDate().format(formatter))
                .derigistrationDate(derigistrationDate == null ? "null" : derigistrationDate.format(formatter))
                .producer(computer.get().getProducer())
                .operationSystem(computer.get().getOperationSystem())
                .display(computer.get().getDisplay())
                .motherBoard(computer.get().getMotherBoard())
                .processor(computer.get().getProcessor())
                .amountRam(computer.get().getAmountRam())
                .memory(computer.get().getMemory())
                .amountOzu(computer.get().getAmountOzu())
                .build();
    }

    public void deleteTech(int techId) {
        Optional<Tech> tech = techRepository.findById(techId);
        tech.ifPresent(value -> {
            value.setActive(false);
            value.setDerigistrationDate(LocalDateTime.now());
        });
        techRepository.save(tech.get());
    }

    public void lifeTech(int techId) {
        Optional<Tech> tech = techRepository.findById(techId);

        tech.ifPresent(value -> {
            value.setActive(true);
            value.setDerigistrationDate(null);
        });
        techRepository.save(tech.get());
    }

    public void editCabinet(int techId, int cabinetId) {
        Optional<Tech> tech = techRepository.findById(techId);
        Optional<Cabinet> cabinet = cabinetRepository.findById(cabinetId);

        tech.get().setCabinet(cabinet.get());
        techRepository.save(tech.get());
    }

    public void editEmployee(int techId, int employeeId) {
        Optional<Tech> tech = techRepository.findById(techId);
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        tech.get().setEmployee(employee.get());
        techRepository.save(tech.get());
    }

}
