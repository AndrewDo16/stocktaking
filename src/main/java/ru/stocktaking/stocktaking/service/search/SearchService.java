package ru.stocktaking.stocktaking.service.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stocktaking.stocktaking.model.furniture.Furniture;
import ru.stocktaking.stocktaking.model.tech.Tech;
import ru.stocktaking.stocktaking.repository.furniture.FurnitureRepository;
import ru.stocktaking.stocktaking.repository.tech.TechRepository;

/**
 * Created by AndreyDo16 on 21.12.2023
 */

@Service
public class SearchService {

    private final TechRepository techRepository;
    private final FurnitureRepository furnitureRepository;

    @Autowired
    public SearchService(TechRepository techRepository, FurnitureRepository furnitureRepository) {
        this.techRepository = techRepository;
        this.furnitureRepository = furnitureRepository;
    }

    public Object searchByInventoryNumber(Long searchInventoryNumber) {

        // Выполнение поиска в обеих моделях
        Tech techResults = techRepository.findTechByInventoryNumber(searchInventoryNumber);
        Furniture furnitureResults = furnitureRepository.findFurnitureByInventoryNumber(searchInventoryNumber);

        if (techResults == null) {
            return furnitureResults;
        } else {
            return techResults;
        }
    }

}
