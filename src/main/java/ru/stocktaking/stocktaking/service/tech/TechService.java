package ru.stocktaking.stocktaking.service.tech;

import ru.stocktaking.stocktaking.model.tech.Computer;
import ru.stocktaking.stocktaking.model.tech.Printer;
import ru.stocktaking.stocktaking.model.tech.Tech;
import ru.stocktaking.stocktaking.model.tech.dto.TechComputerInfoDTO;
import ru.stocktaking.stocktaking.model.tech.dto.TechPrinterInfoDTO;
import ru.stocktaking.stocktaking.repository.tech.ComputerRepository;
import ru.stocktaking.stocktaking.repository.tech.PrinterRepository;
import ru.stocktaking.stocktaking.repository.tech.TechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public TechService(TechRepository techRepository, ComputerRepository computerRepository, PrinterRepository printerRepository) {
        this.techRepository = techRepository;
        this.computerRepository = computerRepository;
        this.printerRepository = printerRepository;
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

    private TechPrinterInfoDTO convertTechWithPrinterToTechInfoDTO(Optional<Tech> tech, Optional<Printer> printer) {
        return TechPrinterInfoDTO.builder()
                .id(tech.get().getId())
                .cabinetNumber(tech.get().getCabinet().getCabinetNumber())
                .employeeName(tech.get().getEmployee().getName() + " " + tech.get().getEmployee().getSurname())
                .serialNumber(tech.get().getSerialNumber())
                .inventoryNumber(tech.get().getInventoryNumber())
                .type(tech.get().getType())
                .model(tech.get().getModel())
                .isActive(tech.get().isActive())
                .registrationDate(tech.get().getRegistrationDate())
                .producer(printer.get().getProducer())
                .printType(printer.get().getPrintType())
                .printTechnology(printer.get().getPrintTechnology())
                .printSpeed(printer.get().getPrintSpeed())
                .build();
    }

    private TechComputerInfoDTO convertTechWithComputerToTechInfoDTO(Optional<Tech> tech, Optional<Computer> computer) {
        return TechComputerInfoDTO.builder()
                .id(tech.get().getId())
                .cabinetNumber(tech.get().getCabinet().getCabinetNumber())
                .employeeName(tech.get().getEmployee().getName() + " " + tech.get().getEmployee().getSurname())
                .serialNumber(tech.get().getSerialNumber())
                .inventoryNumber(tech.get().getInventoryNumber())
                .type(tech.get().getType())
                .model(tech.get().getModel())
                .isActive(tech.get().isActive())
                .registrationDate(tech.get().getRegistrationDate())
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
        tech.ifPresent(value -> value.setActive(false));
        techRepository.save(tech.get());
    }

}
