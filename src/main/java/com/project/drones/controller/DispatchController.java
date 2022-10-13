package com.project.drones.controller;

import com.project.drones.model.drone.DroneDomainModel;
import com.project.drones.model.medication.MedicationDomainModel;
import com.project.drones.service.drone.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/drone", produces = MediaType.APPLICATION_JSON_VALUE)
public class DispatchController {

    private final DroneService droneService;

    public DispatchController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerDrone(@Valid @RequestBody DroneDomainModel droneDomainModel) {
        String serial = droneService.registerDrone(droneDomainModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(serial);
    }

    @GetMapping("/loadWithMedications/{serialNumber}")
    public ResponseEntity<String> loadWithMedications(@PathVariable(value = "serialNumber") String serialNumber) {
        droneService.loadDroneWithMedication(serialNumber);
        return ResponseEntity.status(HttpStatus.OK).body("Drone loaded successfully");
    }

    @GetMapping("/getDroneMedications/{serialNumber}")
    public ResponseEntity<List<MedicationDomainModel>> getDroneMedications(@PathVariable(value = "serialNumber") String serialNumber) {
        List<MedicationDomainModel> droneMedications = droneService.getDroneMedications(serialNumber);
        return ResponseEntity.status(HttpStatus.OK).body(droneMedications);
    }

    @GetMapping("/checkAvailableDrone")
    public ResponseEntity<List<DroneDomainModel>> checkAvailableDroneForLoading() {
        List<DroneDomainModel> availableDronesForLoading = droneService.getAvailableDronesForLoading();
        return ResponseEntity.status(HttpStatus.OK).body(availableDronesForLoading);
    }

    @GetMapping("/checkDroneBattery/{serialNumber}")
    public ResponseEntity<String> checkDroneBattery(@PathVariable(value = "serialNumber") String serialNumber) {
        String droneBatteryLevel = droneService.getDroneBatteryLevel(serialNumber);
        return ResponseEntity.status(HttpStatus.OK).body(droneBatteryLevel);
    }


}
