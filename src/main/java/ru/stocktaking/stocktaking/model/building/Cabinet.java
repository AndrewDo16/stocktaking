package ru.stocktaking.stocktaking.model.building;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stocktaking.stocktaking.model.employee.Employee;

import javax.persistence.*;
import java.util.List;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Entity
@Table(name = "cabinet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cabinet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "cabinet")
    private List<Employee> employees;

    @Column(name = "cabinet_number")
    private int cabinetNumber;

    @Column(name = "corp")
    private String corp;

    @Column(name = "floor")
    private int floor;
}
