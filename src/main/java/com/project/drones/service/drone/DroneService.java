package com.project.drones.service.drone;

import com.project.drones.mapper.drone.DroneDomainMapper;
import com.project.drones.mapper.medication.MedicationDomainMapper;
import com.project.drones.model.audit.AuditEntity;
import com.project.drones.model.audit.AuditEvent;
import com.project.drones.model.drone.DroneDomainModel;
import com.project.drones.model.drone.DroneEntity;
import com.project.drones.model.drone.State;
import com.project.drones.model.medication.MedicationDomainModel;
import com.project.drones.model.medication.MedicationEntity;
import com.project.drones.repoistory.DroneRepository;
import com.project.drones.repoistory.MedicationRepository;
import com.project.drones.service.audit.AuditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DroneService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final DroneDomainMapper droneDomainMapper;
    private final MedicationDomainMapper medicationDomainMapper;
    private final AuditService auditService;

    public DroneService(DroneRepository droneRepository, MedicationRepository medicationRepository, DroneDomainMapper droneDomainMapper, MedicationDomainMapper medicationDomainMapper, AuditService auditService) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
        this.droneDomainMapper = droneDomainMapper;
        this.medicationDomainMapper = medicationDomainMapper;
        this.auditService = auditService;
    }

    @Transactional
    public String registerDrone(DroneDomainModel droneDomainModel) {
        Set<MedicationDomainModel> medicationDomainModelList = droneDomainModel.getMedicationDomainModelList();
        DroneEntity droneEntity = droneDomainMapper.mapToEntityModel(droneDomainModel);
        DroneEntity savedDrone = droneRepository.save(droneEntity);

        auditService.createAuditEvent(AuditEntity.builder().event(AuditEvent.registerDrone.getTitle()).droneSerialNo(savedDrone.getSerialNumber()).build());

        Set<MedicationEntity> collect = medicationDomainModelList.stream().map(medicationDomainMapper::mapToEntityModel).collect(Collectors.toSet());
        collect.forEach(medicationEntity -> {
            medicationEntity.setDroneEntity(savedDrone);
            medicationRepository.save(medicationEntity);
            auditService.createAuditEvent(AuditEntity.builder().event(AuditEvent.registerMedication.getTitle()).droneSerialNo(savedDrone.getSerialNumber()).build());
        });

        return savedDrone.getSerialNumber();
    }

    public void loadDroneWithMedication(String serialNumber) {
        DroneEntity bySerialNumber = droneRepository.findBySerialNumber(serialNumber);

        long medicationsTotalWeight = bySerialNumber.getMedicationEntitySet().stream().mapToLong(MedicationEntity::getWeight).sum();
        if (medicationsTotalWeight > bySerialNumber.getWeightLimit() || bySerialNumber.getBatteryCapacity() < 25) {
            throw new RuntimeException("can't load the drone for Heavy load");
        }

        bySerialNumber.setState(State.LOADED);
        droneRepository.save(bySerialNumber);
        auditService.createAuditEvent(AuditEntity.builder().event(AuditEvent.droneLoaded.getTitle()).droneSerialNo(serialNumber).build());
    }


    public List<MedicationDomainModel> getDroneMedications(String droneSerialNumber) {
        List<MedicationEntity> medicationEntities = medicationRepository.getAllByDroneEntity_SerialNumber(droneSerialNumber);
        return medicationEntities.stream().map(medicationDomainMapper::mapToDomainModel).collect(Collectors.toList());
    }

    public List<DroneDomainModel> getAvailableDronesForLoading() {
        List<DroneEntity> idleDrones = droneRepository.getAllByStateAndBatteryCapacityGreaterThan(State.IDLE, 24);
        return idleDrones.stream().map(droneDomainMapper::mapToDomainModel).collect(Collectors.toList());
    }

    public String getDroneBatteryLevel(String droneSerialNumber) {
        DroneEntity bySerialNumber = droneRepository.getBySerialNumber(droneSerialNumber);
        return String.valueOf(bySerialNumber.getBatteryCapacity().intValue()).concat("% .");
    }

}
