package ru.stocktaking.stocktaking.model.building.dto;

import lombok.Data;
import ru.stocktaking.stocktaking.model.building.Building;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Data
public class BuildingDTO {

    private Building building;
    private long totalCabinets;
    private long totalTech;
}
