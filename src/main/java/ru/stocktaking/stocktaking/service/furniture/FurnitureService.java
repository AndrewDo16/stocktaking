package ru.stocktaking.stocktaking.service.furniture;

import org.springframework.stereotype.Service;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.model.furniture.Furniture;
import ru.stocktaking.stocktaking.model.furniture.dto.FurnitureDTO;
import ru.stocktaking.stocktaking.model.tech.Tech;
import ru.stocktaking.stocktaking.repository.building.CabinetRepository;
import ru.stocktaking.stocktaking.repository.furniture.FurnitureRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Service
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;
    private final CabinetRepository cabinetRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public FurnitureService(FurnitureRepository furnitureRepository, CabinetRepository cabinetRepository) {
        this.furnitureRepository = furnitureRepository;
        this.cabinetRepository = cabinetRepository;
    }

    public List<Furniture> findByCabinetId(int cabinetId) {
        return furnitureRepository.findFurnitureByCabinetId(cabinetId);
    }

    public Optional<FurnitureDTO> findById(int furnitureId) {
        return furnitureRepository.findById(furnitureId).map(this::convertFurnitureToDTO);

    }

    private FurnitureDTO convertFurnitureToDTO(Furniture furniture) {

        LocalDateTime derigistrationDate = furniture.getDerigistrationDate();

        return FurnitureDTO.builder()
                .id(furniture.getId())
                .building(furniture.getCabinet().getBuilding())
                .cabinetNumber(furniture.getCabinet().getCabinetNumber())
                .inventoryNumber(furniture.getInventoryNumber())
                .type(furniture.getType())
                .model(furniture.getModel())
                .producer(furniture.getProducer())
                .material(furniture.getMaterial())
                .isActive(furniture.isActive())
                .registrationDate(furniture.getRegistrationDate().format(formatter))
                .derigistrationDate((derigistrationDate != null) ? derigistrationDate.format(formatter) : "null")
                .build();
    }

    public void deleteFurniture(int furnitureId) {
        Optional<Furniture> furniture = furnitureRepository.findById(furnitureId);
        furniture.ifPresent(value -> {
            value.setActive(false);
            value.setDerigistrationDate(LocalDateTime.now());
        });
        furnitureRepository.save(furniture.get());
    }

    public void lifeFurniture(int furnitureId) {
        Optional<Furniture> furniture = furnitureRepository.findById(furnitureId);
        furniture.ifPresent(value -> {
            value.setActive(true);
            value.setDerigistrationDate(null);
        });
        furnitureRepository.save(furniture.get());
    }

    public void editCabinet(int furnitureId, int cabinetId) {
        Optional<Furniture> furniture = furnitureRepository.findById(furnitureId);
        Optional<Cabinet> cabinet = cabinetRepository.findById(cabinetId);

        furniture.get().setCabinet(cabinet.get());
        furnitureRepository.save(furniture.get());
    }

}
