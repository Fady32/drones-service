package com.project.drones.model.base;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseAuditModel implements Serializable {
    private static final long serialVersionUID = -8036100350153949873L;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;

    @PrePersist
    public void preInsert() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
