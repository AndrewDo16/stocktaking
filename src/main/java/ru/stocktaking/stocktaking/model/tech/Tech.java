package ru.stocktaking.stocktaking.model.tech;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stocktaking.stocktaking.model.building.Cabinet;

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

    @ManyToOne
    @JoinColumn(name = "cabinet_id")
    private Cabinet cabinet;

    @Column(name = "serial_number", unique = true)
    private long serialNumber;

    @Column(name = "inventory_number", unique = true)
    private long inventoryNumber;

    @Column(name = "type")
    private String type;

    @Column(name = "model")
    private String model;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "registration_date")
    private Timestamp registrationDate;
}
