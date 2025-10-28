package com.ibm.sk.fots.spring.repository.custom;

import com.ibm.sk.fots.spring.entity.TaskEntity;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepositoryCustom {

  List<TaskEntity> findByCompletedAndDueDateBefore(boolean completed, LocalDate dueDate);
}
