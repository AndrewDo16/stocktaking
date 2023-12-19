package ru.stocktaking.stocktaking.model.tech;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Entity
@Table(name = "computer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "tech_id")
    private Tech tech;

    @Column(name = "producer")
    private String producer;

    @Column(name = "model")
    private String model;

    @Column(name = "operation_system")
    private String operationSystem;

    @Column(name = "display")
    private String display;

    @Column(name = "motherboard")
    private String motherBoard;

    @Column(name = "processor")
    private String processor;

    @Column(name = "amountRam")
    private String amountRam;

    @Column(name = "memory")
    private String memory;

    @Column(name = "amountOzu")
    private String amountOzu;
}
