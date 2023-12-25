package ru.stocktaking.stocktaking.repository.tech;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stocktaking.stocktaking.model.building.Cabinet;
import ru.stocktaking.stocktaking.model.tech.Tech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AndreyDo16 on 19.12.2023
 */

@Repository
public interface TechRepository extends JpaRepository<Tech, Integer> {
    @Query("SELECT COUNT(t) FROM Tech t WHERE t.isActive = true AND t.cabinet = :cabinet")
    long countActiveTechByCabinet(@Param("cabinet") Cabinet cabinet);
    boolean existsByCabinetId(int cabinetId);

    @Query("SELECT tech FROM Tech tech WHERE tech.isActive = true AND tech.cabinet.id = :cabinetId")
    List<Tech> findTechByCabinetId(@Param("cabinetId") int cabinetId);

    Tech findTechByInventoryNumber(long inventoryNumber);
}
