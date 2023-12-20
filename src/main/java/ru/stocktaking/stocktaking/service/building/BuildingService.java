package ru.stocktaking.stocktaking.service.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stocktaking.stocktaking.model.building.Building;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.repository.building.BuildingRepository;
import ru.stocktaking.stocktaking.repository.building.CabinetRepository;
import ru.stocktaking.stocktaking.repository.tech.TechRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final CabinetRepository cabinetRepository;
    private final TechRepository techRepository;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository, CabinetRepository cabinetRepository, TechRepository techRepository) {
        this.buildingRepository = buildingRepository;
        this.cabinetRepository = cabinetRepository;
        this.techRepository = techRepository;
    }

    public List<Building> findAllBuilding() {
        return buildingRepository.findAll();
    }

    public long getTotalCabinetsForBuilding(Building building) {
        return cabinetRepository.countByBuilding(building);
    }

    public long getTotalTechForBuilding(Building building) {
        List<Cabinet> cabinets = cabinetRepository.findByBuilding(building);
        long totalTech = 0;

        for (Cabinet cabinet : cabinets) {
            totalTech += techRepository.countByCabinet(cabinet);
        }

        return totalTech;
    }

    public Optional <Building> findOneBuilding(int buildingId) {
        return buildingRepository.findById(buildingId);
    }
}
