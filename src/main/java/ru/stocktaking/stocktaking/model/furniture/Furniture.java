package ru.stocktaking.stocktaking.model.furniture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stocktaking.stocktaking.model.building.Cabinet;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "furniture")
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cabinet_id")
    private Cabinet cabinet;

    @Column(name = "inventory_number", unique = true)
    private long inventoryNumber;

    @Column(name = "type")
    private String type;

    @Column(name = "model")
    private String model;

    @Column(name = "producer")
    private String producer;

    @Column(name = "material")
    private String material;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "registration_date")
    private Timestamp registrationDate;

}
