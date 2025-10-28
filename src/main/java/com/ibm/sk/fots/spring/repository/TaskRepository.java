package com.ibm.sk.fots.spring.repository;

import com.ibm.sk.fots.spring.entity.TaskEntity;
import com.ibm.sk.fots.spring.repository.custom.TaskRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long>, TaskRepositoryCustom {

  Optional<TaskEntity> findByTaskId(Long taskId);

  @Query("SELECT t FROM TaskEntity t WHERE t.completed = :completed")
  List<TaskEntity> findByCompleted(boolean completed);

  List<TaskEntity> findByDueDateBefore(LocalDate dueDate);

}
