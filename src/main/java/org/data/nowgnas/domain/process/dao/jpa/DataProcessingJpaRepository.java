package org.data.nowgnas.domain.process.dao.jpa;

import org.data.nowgnas.domain.process.dao.entity.DataProcessingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataProcessingJpaRepository extends JpaRepository<DataProcessingEntity, Long> {}
