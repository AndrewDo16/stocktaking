package ru.stocktaking.stocktaking.repository.tech;

import ru.stocktaking.stocktaking.model.tech.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Integer> {
}
