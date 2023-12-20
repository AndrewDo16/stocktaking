package ru.stocktaking.stocktaking.repository.furniture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stocktaking.stocktaking.model.furniture.Furniture;

import java.util.List;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
    List<Furniture> findFurnitureByCabinetId(int cabinetId);
    boolean existsByCabinetId(int cabinetId);
}
