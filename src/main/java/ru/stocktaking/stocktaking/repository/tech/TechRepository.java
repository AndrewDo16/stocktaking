package ru.stocktaking.stocktaking.repository.tech;

import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.model.tech.Tech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Repository
public interface TechRepository extends JpaRepository<Tech, Integer> {
    long countByCabinet(Cabinet cabinet);

    boolean existsByCabinetId(int cabinetId);

    List<Tech> findTechByCabinetId(int cabinetId);

    Tech findTechByInventoryNumber(long inventoryNumber);
}
