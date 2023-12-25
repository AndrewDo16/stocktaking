package ru.stocktaking.stocktaking.service.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stocktaking.stocktaking.model.building.Building;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.repository.building.BuildingRepository;
import ru.stocktaking.stocktaking.repository.building.CabinetRepository;
import ru.stocktaking.stocktaking.repository.furniture.FurnitureRepository;
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
    private final FurnitureRepository furnitureRepository;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository, CabinetRepository cabinetRepository, TechRepository techRepository, FurnitureRepository furnitureRepository) {
        this.buildingRepository = buildingRepository;
        this.cabinetRepository = cabinetRepository;
        this.techRepository = techRepository;
        this.furnitureRepository = furnitureRepository;
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
            totalTech += techRepository.countActiveTechByCabinet(cabinet);
        }

        return totalTech;
    }

    public long getTotalFurnitureForBuilding(Building building) {
        List<Cabinet> cabinets = cabinetRepository.findByBuilding(building);
        long totalFurniture = 0;

        for (Cabinet cabinet : cabinets) {
            totalFurniture += furnitureRepository.countActiveFurnitureByCabinet(cabinet);
        }

        return totalFurniture;
    }

    public Optional <Building> findOneBuilding(int buildingId) {
        return buildingRepository.findById(buildingId);
    }
}
