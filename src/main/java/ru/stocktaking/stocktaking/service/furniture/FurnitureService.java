package ru.stocktaking.stocktaking.service.furniture;

import org.springframework.stereotype.Service;
import ru.stocktaking.stocktaking.model.furniture.Furniture;
import ru.stocktaking.stocktaking.model.tech.Tech;
import ru.stocktaking.stocktaking.repository.furniture.FurnitureRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Service
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public List<Furniture> findByCabinetId(int cabinetId) {
        return furnitureRepository.findFurnitureByCabinetId(cabinetId);
    }

    public Optional<Furniture> findById(int furnitureId) {
        return furnitureRepository.findById(furnitureId);
    }

    public void deleteFurniture(int furnitureId) {
        Optional<Furniture> furniture = furnitureRepository.findById(furnitureId);
        furniture.ifPresent(value -> value.setActive(false));
        furnitureRepository.save(furniture.get());
    }

}
