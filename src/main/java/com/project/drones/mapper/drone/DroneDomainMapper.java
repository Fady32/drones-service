package com.project.drones.mapper.drone;

import com.project.drones.mapper.medication.MedicationDomainMapper;
import com.project.drones.model.base.Mapper;
import com.project.drones.model.drone.DroneDomainModel;
import com.project.drones.model.drone.DroneEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DroneDomainMapper implements Mapper<DroneDomainModel, DroneEntity> {

    private final MedicationDomainMapper medicationDomainMapper;

    public DroneDomainMapper(MedicationDomainMapper medicationDomainMapper) {
        this.medicationDomainMapper = medicationDomainMapper;
    }

    @Override
    public DroneDomainModel mapToDomainModel(DroneEntity boundedModel) throws RuntimeException {

        DroneDomainModel droneDomain = DroneDomainModel.builder().serialNumber(boundedModel.getSerialNumber())
                .model(boundedModel.getModel())
                .state(boundedModel.getState())
                .batteryCapacity(boundedModel.getBatteryCapacity())
                .weightLimit(boundedModel.getWeightLimit())
                .build();

        if (boundedModel.getMedicationEntitySet() != null) {
            droneDomain.setMedicationDomainModelList(boundedModel.getMedicationEntitySet().stream().map(medicationDomainMapper::mapToDomainModel).collect(Collectors.toSet()))
            ;
        }

        updateAuditFields(droneDomain, boundedModel);
        return droneDomain;
    }

    @Override
    public DroneEntity mapToEntityModel(DroneDomainModel domainModel) throws RuntimeException {
        DroneEntity droneEntity = DroneEntity.builder().serialNumber(domainModel.getSerialNumber())
                .model(domainModel.getModel())
                .state(domainModel.getState())
                .batteryCapacity(domainModel.getBatteryCapacity())
                .weightLimit(domainModel.getWeightLimit())
                .build();


        return droneEntity;
    }
}
