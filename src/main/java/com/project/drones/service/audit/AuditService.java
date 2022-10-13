package com.project.drones.service.audit;

import com.project.drones.model.audit.AuditEntity;
import com.project.drones.repoistory.AuditRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class AuditService {

    private final AuditRepository auditRepository;

    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;

    }

    @Transactional
    public void createAuditEvent(AuditEntity auditEntity) {
        auditEntity.setId(UUID.randomUUID());
        auditRepository.save(auditEntity);
    }

}
