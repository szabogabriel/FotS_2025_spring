package com.ibm.sk.fots.spring.repository;

import com.ibm.sk.fots.spring.dto.Task;
import com.ibm.sk.fots.spring.entity.TagEntity;
import com.ibm.sk.fots.spring.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TaskRepository {

  private AtomicLong latsgeneratedId = new AtomicLong(0);
  private List<TaskEntity> storedTaskEntityList = new ArrayList<>();

  public Optional<TaskEntity> findByTaskId(Long taskId) {
    return storedTaskEntityList.stream().filter(t -> t.getTaskId().equals(taskId)).findFirst();
  }

  public List<TaskEntity> findByCompleted(boolean completed) {
    return storedTaskEntityList.stream().filter(TaskEntity::isCompleted).collect(Collectors.toList());
  }

  public List<TaskEntity> findByDueDateBefore(LocalDate dueDate) {
    return storedTaskEntityList.stream()
        .filter(t -> t.getDueDate() != null && t.getDueDate().isBefore(dueDate.atStartOfDay()))
        .collect(Collectors.toList());
  }

  public List<TaskEntity> findByCompletedAndDueDateBefore(boolean completed, LocalDate dueDate) {
    return storedTaskEntityList.stream()
        .filter(t -> t.isCompleted() && (t.getDueDate() != null && t.getDueDate().isBefore(dueDate.atStartOfDay())))
        .collect(Collectors.toList());
  }

  public TaskEntity save(TaskEntity e) {
    if (e.getId() == null) {
      e.setId(latsgeneratedId.incrementAndGet());
      storedTaskEntityList.add(e);
    }

    return e;
  }

  public TaskEntity delete(TaskEntity e) {
    Optional<TaskEntity> foundEntity = storedTaskEntityList.stream().filter(t -> t.getId().equals(e.getId()))
        .findFirst();

    if (foundEntity.isPresent()) {
      storedTaskEntityList.remove(foundEntity.get());
    }

    return foundEntity.orElse(null);
  }
}
