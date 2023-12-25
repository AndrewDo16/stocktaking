package ru.stocktaking.stocktaking.model.tech.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Data
@Builder
public class TechPrinterInfoDTO {

    private int id;

    private int cabinetNumber;

    private int cabinetId;

    private String employeeName;

    private int employeeId;

    private long serialNumber;

    private long inventoryNumber;

    private String type;

    private String model;

    private boolean isActive;

    private String registrationDate;

    private String derigistrationDate;

    private String producer;

    private String printType;

    private String printTechnology;

    private String printSpeed;
}


