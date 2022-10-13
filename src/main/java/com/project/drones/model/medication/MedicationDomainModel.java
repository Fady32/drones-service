package com.project.drones.model.medication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.drones.model.base.DomainModel;
import com.project.drones.model.drone.DroneDomainModel;
import com.project.drones.model.drone.DroneEntity;
import com.project.drones.model.drone.Model;
import com.project.drones.model.drone.State;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicationDomainModel extends DomainModel {

    @NotNull(message = "Serial Number shouldn't be empty.")
    private String code;

    private String name;

    @Range(min = 0, max = 500, message = "Weight Limit Capacity from 0 to 500 max")
    private Integer weight;

    private byte[] image;

    private String imageContentType;

    private DroneDomainModel droneDomainModel;

}
