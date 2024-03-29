package ru.stocktaking.stocktaking.repository.building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stocktaking.stocktaking.model.building.Building;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
}
