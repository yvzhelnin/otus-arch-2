package ru.yvzhelnin.otus.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yvzhelnin.otus.warehouse.enums.EquipmentStatus;
import ru.yvzhelnin.otus.warehouse.model.Equipment;
import ru.yvzhelnin.otus.warehouse.model.Model;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    List<Equipment> findAllByEquipmentStatus(EquipmentStatus equipmentStatus);

    List<Equipment> findAllByModelAndEquipmentStatus(Model model, EquipmentStatus equipmentStatus);
}
