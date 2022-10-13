package com.project.drones.repoistory;

import com.project.drones.mapper.drone.DroneDomainMapper;
import com.project.drones.model.drone.DroneEntity;
import com.project.drones.model.drone.State;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends PagingAndSortingRepository<DroneEntity, String> {
    List<DroneEntity> findAll();

    DroneEntity getBySerialNumber(String serial);

    List<DroneEntity> getAllByStateAndBatteryCapacityGreaterThan(State state, Integer batteryPercent);

    DroneEntity findBySerialNumber(String serial);
}
  