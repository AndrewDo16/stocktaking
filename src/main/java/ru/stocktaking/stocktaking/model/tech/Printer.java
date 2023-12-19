package ru.stocktaking.stocktaking.model.tech;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Entity
@Table(name = "printer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @OneToOne
    @JoinColumn(name = "tech_id")
    private Tech tech;

    @Column(name = "producer")
    private String producer;

    @Column(name = "model")
    private String model;

    @Column(name = "print_type")
    private String printType;

    @Column(name = "print_technology")
    private String printTechnology;

    @Column(name = "printSpeed")
    private String printSpeed;
}
