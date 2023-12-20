package ru.stocktaking.stocktaking.service.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stocktaking.stocktaking.repository.building.CabinetRepository;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Service
public class CabinetService {

    private final CabinetRepository cabinetRepository;

    @Autowired
    public CabinetService(CabinetRepository cabinetRepository) {
        this.cabinetRepository = cabinetRepository;
    }



}
