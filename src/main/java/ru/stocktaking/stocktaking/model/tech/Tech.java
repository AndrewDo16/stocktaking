package ru.stocktaking.stocktaking.model.tech;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tech")
public class Tech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "serial_number", unique = true)
    @Min(value = 1, message = "Поле \"Серийный номер\" должно содержать минимум 1 символ")
    private long serialNumber;

    @Column(name = "inventory_number", unique = true)
    @Min(value = 1, message = "Поле \"Инвентарный номер\" должно содержать минимум 1 символ")
    private long inventoryNumber;

    @Column(name = "type")
    @NotEmpty(message = "Поле \"Тип техники\" не может быть пустым")
    @Size(min = 2, max = 30, message = "Поле \"Тип техники\" должен содержать не менее 2 и не больше 30 символов")
    private String type;

    @Column(name = "model")
    @NotEmpty(message = "Поле \"Тип техники\" не может быть пустым")
    @Size(min = 2, max = 30, message = "Поле \"Модель\" должен содержать не менее 2 и не больше 30 символов")
    private String model;

    @Column(name = "cabinet_number")
    @Min(value = 2, message = "Поле \"Номер кабинета\" должно содержать минимум 2 символа")
    private int cabinetNumber;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "registration_date")
    private Timestamp registrationDate;
}
