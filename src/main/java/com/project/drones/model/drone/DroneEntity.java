package com.project.drones.model.drone;

import com.project.drones.model.base.BaseAuditModel;
import com.project.drones.model.medication.MedicationEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "drone")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneEntity extends BaseAuditModel {

    @Id
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    private Model model;

    @NotNull
    @Column(name = "weight_limit")
    private Integer weightLimit;

    @NotNull
    @Column(name = "battery_capacity")
    private Integer batteryCapacity;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @OneToMany(mappedBy = "droneEntity", fetch = FetchType.EAGER)
    private Set<MedicationEntity> medicationEntitySet;

}
