package ru.stocktaking.stocktaking.model.building;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stocktaking.stocktaking.model.employee.Employee;

import javax.persistence.*;
import java.util.List;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Entity
@Table(name = "department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "building_id")
    private Building building;

}
