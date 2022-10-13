package com.project.drones.scheduler;

import com.project.drones.model.audit.AuditEntity;
import com.project.drones.model.audit.AuditEvent;
import com.project.drones.model.drone.DroneEntity;
import com.project.drones.repoistory.DroneRepository;
import com.project.drones.service.audit.AuditService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CheckDroneBatteryDispatcher {

    private final DroneRepository droneRepository;
    private final AuditService auditService;

    public CheckDroneBatteryDispatcher(DroneRepository droneRepository, AuditService auditService) {
        this.droneRepository = droneRepository;
        this.auditService = auditService;
    }

    @Scheduled(fixedRate = 3, timeUnit = TimeUnit.HOURS)
    void checkDronesBattery() {
        for (DroneEntity droneEntity : droneRepository.findAll()) {
            auditService.createAuditEvent(AuditEntity.builder().event(AuditEvent.droneBattery.getTitle()
                    .replace("$battery", droneEntity.getBatteryCapacity().toString())).droneSerialNo(droneEntity.getSerialNumber()).build());

        }
    }

}
