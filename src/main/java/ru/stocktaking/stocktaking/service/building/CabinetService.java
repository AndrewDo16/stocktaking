package ru.stocktaking.stocktaking.service.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.repository.building.CabinetRepository;
import ru.stocktaking.stocktaking.repository.tech.TechRepository;

import java.util.List;

/**
 * Created by AndreyDo16 on 20.12.2023
 */

@Service
public class CabinetService {

    private final CabinetRepository cabinetRepository;
    private final TechRepository techRepository;

    @Autowired
    public CabinetService(CabinetRepository cabinetRepository, TechRepository techRepository) {
        this.cabinetRepository = cabinetRepository;
        this.techRepository = techRepository;
    }


    public List<Cabinet> findCabinetByDepartmentId(int departmentId) {
        return cabinetRepository.findCabinetsByDepartmentId(departmentId);
    }

    public long getTotalTechForCabinet(Cabinet cabinet) {
        return techRepository.countByCabinet(cabinet);
    }

}
