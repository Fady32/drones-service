package com.project.drones.model.audit;

import com.project.drones.model.base.BaseAuditModel;
import com.project.drones.model.drone.Model;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "audit")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEntity extends BaseAuditModel {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "event")
    private String event;

    @NotNull
    @Column(name = "drone_number")
    private String droneSerialNo;
}
