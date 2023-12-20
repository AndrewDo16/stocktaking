package ru.stocktaking.stocktaking.model.building.dto;

import lombok.Data;
import ru.stocktaking.stocktaking.model.building.Department;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Data
public class DepartmentDTO {

    private Department department;
    private long totalCabinets;
    private long totalTech;
}
