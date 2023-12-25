package ru.stocktaking.stocktaking.repository.furniture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.model.furniture.Furniture;
import ru.stocktaking.stocktaking.model.tech.Tech;

import java.util.List;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
    @Query("SELECT furniture FROM Furniture furniture WHERE furniture.isActive = true AND furniture.cabinet.id = :cabinetId")
    List<Furniture> findFurnitureByCabinetId(@Param("cabinetId") int cabinetId);
    boolean existsByCabinetId(int cabinetId);

    @Query("SELECT COUNT(furniture) FROM Furniture furniture WHERE furniture.isActive = true AND furniture.cabinet = :cabinet")
    long countActiveFurnitureByCabinet(@Param("cabinet") Cabinet cabinet);

    Furniture findFurnitureByInventoryNumber(long inventoryNumber);
}
