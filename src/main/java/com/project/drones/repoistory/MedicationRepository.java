package com.project.drones.repoistory;

import com.project.drones.model.medication.MedicationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends PagingAndSortingRepository<MedicationEntity, String> {
    List<MedicationEntity> findAll();

    List<MedicationEntity> getAllByDroneEntity_SerialNumber(String serial);
}
  