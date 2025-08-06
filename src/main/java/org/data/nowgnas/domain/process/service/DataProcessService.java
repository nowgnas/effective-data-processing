package org.data.nowgnas.domain.process.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.data.nowgnas.domain.process.dao.entity.DataProcessingEntity;
import org.data.nowgnas.domain.process.dao.entity.QDataProcessingEntity;
import org.data.nowgnas.domain.process.dao.jpa.DataProcessingJpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataProcessService {
  private final DataProcessingJpaRepository dataProcessingJpaRepository;
  private final JPAQueryFactory queryFactory;

  public DataProcessingEntity selectById(Long id) {
    return dataProcessingJpaRepository.findById(id).orElse(null);
  }

  public DataProcessingEntity selectByIdWithQueryDsl(Long id) {
    QDataProcessingEntity dataProcessingEntity = QDataProcessingEntity.dataProcessingEntity;
    return queryFactory
        .selectFrom(dataProcessingEntity)
        .where(dataProcessingEntity.id.eq(id))
        .fetchOne();
  }
}