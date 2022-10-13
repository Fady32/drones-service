package com.project.drones.model.medication;

import com.project.drones.model.base.BaseAuditModel;
import com.project.drones.model.drone.DroneEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "medication")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationEntity extends BaseAuditModel {

    @Id
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @ManyToOne
    private DroneEntity droneEntity;
}
