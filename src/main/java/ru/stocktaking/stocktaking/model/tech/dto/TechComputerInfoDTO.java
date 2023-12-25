package ru.stocktaking.stocktaking.model.tech.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Builder
@Data
public class TechComputerInfoDTO {

    private int id;

    private int cabinetNumber;

    private String employeeName;

    private long serialNumber;

    private long inventoryNumber;

    private String type;

    private String model;

    private boolean isActive;

    private Timestamp registrationDate;


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



