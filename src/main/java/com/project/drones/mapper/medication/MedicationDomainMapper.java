package com.project.drones.mapper.medication;

import com.project.drones.model.base.Mapper;
import com.project.drones.model.drone.DroneDomainModel;
import com.project.drones.model.drone.DroneEntity;
import com.project.drones.model.medication.MedicationDomainModel;
import com.project.drones.model.medication.MedicationEntity;
import org.springframework.stereotype.Component;

@Component
public class MedicationDomainMapper implements Mapper<MedicationDomainModel, MedicationEntity> {


    @Override
    public MedicationDomainModel mapToDomainModel(MedicationEntity boundedModel) throws RuntimeException {

        MedicationDomainModel medicationDomainModel = MedicationDomainModel.builder()
                .code(boundedModel.getCode())
                .name(boundedModel.getName())
                .weight(boundedModel.getWeight())
                .image(boundedModel.getImage())
                .imageContentType(boundedModel.getImageContentType())
                .build();

        if (boundedModel.getDroneEntity() != null) {
            medicationDomainModel.setDroneDomainModel(DroneDomainModel.builder().serialNumber(boundedModel.getDroneEntity().getSerialNumber())
                    .model(boundedModel.getDroneEntity().getModel())
                    .state(boundedModel.getDroneEntity().getState())
                    .batteryCapacity(boundedModel.getDroneEntity().getBatteryCapacity())
                    .weightLimit(boundedModel.getDroneEntity().getWeightLimit())
                    .build());
        }

        updateAuditFields(medicationDomainModel, boundedModel);
        return medicationDomainModel;
    }

    @Override
    public MedicationEntity mapToEntityModel(MedicationDomainModel domainModel) throws RuntimeException {
        MedicationEntity medicationEntity = MedicationEntity.builder()
                .code(domainModel.getCode())
                .name(domainModel.getName())
                .weight(domainModel.getWeight())
                .image(domainModel.getImage())
                .imageContentType(domainModel.getImageContentType())
                .build();

        if (domainModel.getDroneDomainModel() != null) {
            medicationEntity.setDroneEntity(DroneEntity.builder().serialNumber(domainModel.getDroneDomainModel().getSerialNumber())
                    .model(domainModel.getDroneDomainModel().getModel())
                    .state(domainModel.getDroneDomainModel().getState())
                    .batteryCapacity(domainModel.getDroneDomainModel().getBatteryCapacity())
                    .weightLimit(domainModel.getDroneDomainModel().getWeightLimit())
                    .build());
        }
        return medicationEntity;
    }
}
