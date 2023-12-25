package ru.stocktaking.stocktaking.service.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.repository.building.CabinetRepository;
import ru.stocktaking.stocktaking.repository.furniture.FurnitureRepository;
import ru.stocktaking.stocktaking.repository.tech.TechRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Service
public class CabinetService {

    private final CabinetRepository cabinetRepository;
    private final TechRepository techRepository;
    private final FurnitureRepository furnitureRepository;

    @Autowired
    public CabinetService(CabinetRepository cabinetRepository, TechRepository techRepository, FurnitureRepository furnitureRepository) {
        this.cabinetRepository = cabinetRepository;
        this.techRepository = techRepository;
        this.furnitureRepository = furnitureRepository;
    }

    public Optional<Cabinet> findById(int cabinetId) {
        return cabinetRepository.findById(cabinetId);
    }

    public List<Cabinet> findCabinetByDepartmentId(int departmentId) {
        return cabinetRepository.findCabinetsByDepartmentId(departmentId);
    }

    public long getTotalTechForCabinet(Cabinet cabinet) {
        return techRepository.countActiveTechByCabinet(cabinet);
    }

    public long getTotalFurnitureForCabinet(Cabinet cabinet) {
        return furnitureRepository.countActiveFurnitureByCabinet(cabinet);
    }

    public boolean hasTechInCabinet(int cabinetId) {
        return techRepository.existsByCabinetId(cabinetId);
    }

    public boolean hasFurnitureInCabinet(int cabinetId) {
        return furnitureRepository.existsByCabinetId(cabinetId);
    }

}
