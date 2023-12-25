package ru.stocktaking.stocktaking.model.tech.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Data
@Builder
public class TechPrinterInfoDTO {

    private int id;

    private int cabinetNumber;

    private String employeeName;

    private long serialNumber;

    private long inventoryNumber;

    private String type;

    private String model;

    private boolean isActive;

    private Timestamp registrationDate;

    private String producer;

    private String printType;

    private String printTechnology;

    private String printSpeed;
}


