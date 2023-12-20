package ru.stocktaking.stocktaking.model.building.dto;

import lombok.Data;
import ru.stocktaking.stocktaking.model.building.Cabinet;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Data
public class CabinetDTO {
    private Cabinet cabinet;
    private long totalTech;
}
