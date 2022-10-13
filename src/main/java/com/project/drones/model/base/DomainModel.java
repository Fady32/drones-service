/*@author: mohamed abdo, at: Jan 2022 */
package com.project.drones.model.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class DomainModel implements Serializable {


    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;


}
