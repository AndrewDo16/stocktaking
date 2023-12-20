package ru.stocktaking.stocktaking.repository.building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stocktaking.stocktaking.model.building.Building;
import ru.stocktaking.stocktaking.model.building.Cabinet;

import java.util.List;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, Integer> {
    long countByBuilding(Building building);

    List<Cabinet> findByBuilding(Building building);
}
