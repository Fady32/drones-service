package com.project.drones.repoistory;

import com.project.drones.model.audit.AuditEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuditRepository extends PagingAndSortingRepository<AuditEntity, UUID> {

}
  