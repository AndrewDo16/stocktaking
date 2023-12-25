package ru.stocktaking.stocktaking.model.tech.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Builder
@Data
public class TechComputerInfoDTO {

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


//    computer

    private String producer;

    private String operationSystem;

    private String display;

    private String motherBoard;

    private String processor;

    private String amountRam;

    private String memory;

    private String amountOzu;


}



