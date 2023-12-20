package ru.stocktaking.stocktaking.service.tech;

import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.repository.tech.TechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Service
public class TechService {

    private final TechRepository techRepository;

    @Autowired
    public TechService(TechRepository techRepository) {
        this.techRepository = techRepository;
    }


}
