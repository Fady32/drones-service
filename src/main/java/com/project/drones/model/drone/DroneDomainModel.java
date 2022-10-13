package com.project.drones.model.drone;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.drones.model.base.DomainModel;
import com.project.drones.model.medication.MedicationDomainModel;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DroneDomainModel extends DomainModel {

    @NotNull(message = "Serial Number shouldn't be empty.")
    @Pattern(regexp = "^[0-9A-Za-z!@.,;:'\"?-]{1,100}\\z", message = "Serial Number should be a valid Serial Number")
    private String serialNumber;

    @NotNull(message = "Model shouldn't be empty.")
    private Model model;

    @Range(min = 0, max = 500, message = "Weight Limit Capacity from 0 to 500 max")
    private Integer weightLimit;

    @Range(min = 0, max = 100, message = "Battery Capacity from 0 to 100 only")
    private Integer batteryCapacity;

    @NotNull(message = "State shouldn't be empty.")
    private State state;

    private Set<MedicationDomainModel> medicationDomainModelList;

}
