package com.project.drones.model.base;

import javax.validation.Valid;

public interface Mapper<T extends DomainModel, R extends BaseAuditModel> {

    T mapToDomainModel(@Valid R boundedModel) throws RuntimeException;

    R mapToEntityModel(@Valid T domainModel) throws RuntimeException;

    default void updateAuditFields(T t, R r) {
        t.setCreatedAt(r.getCreatedAt());
        t.setCreatedBy(r.getCreatedBy());
        t.setUpdatedAt(r.getUpdatedAt());
        t.setUpdatedBy(r.getUpdatedBy());
    }

}
