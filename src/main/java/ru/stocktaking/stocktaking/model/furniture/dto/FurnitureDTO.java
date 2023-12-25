package ru.stocktaking.stocktaking.model.furniture.dto;

import lombok.Builder;
import lombok.Data;
import ru.stocktaking.stocktaking.model.building.Building;
import ru.stocktaking.stocktaking.model.building.Cabinet;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Created by AndreyDo16 on 25.12.2023
 */

@Builder
@Data
public class FurnitureDTO {

    private int id;

    private Building building;

    private int cabinetNumber;

    private long inventoryNumber;

    private String type;

    private String model;

    private String producer;

    private String material;

    private boolean isActive;

    private String registrationDate;

    private String derigistrationDate;
}
