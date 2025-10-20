package com.ibm.sk.fots.spring.mapper;

import com.ibm.sk.fots.spring.dto.PriorityEnum;
import com.ibm.sk.fots.spring.dto.Task;
import com.ibm.sk.fots.spring.dto.TaskCreate;
import com.ibm.sk.fots.spring.dto.TaskUpdate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskMapper {

  public static Task toNew(TaskCreate dto) {
    if (dto == null) {
      return null;
    }

    Task task = new Task();
    task.setTitle(dto.getTitle());
    task.setDescription(dto.getDescription());
    task.setCompleted(dto.isCompleted());

    return task;
  }

  public static Task update(Task storedTask, TaskUpdate update) {
    if (storedTask == null || update == null) {
      return storedTask;
    }

    if (update.getTitle() != null) {
      storedTask.setTitle(update.getTitle());
    }
    if (update.getDescription() != null) {
      storedTask.setDescription(update.getDescription());
    }
    storedTask.setCompleted(update.isCompleted());
    if (update.getDueDate() != null) {
      storedTask.setDueDate(toLocalDateTime(update.getDueDate()));
    }
    if (update.getPriority() != null) {
      storedTask.setPriority(PriorityEnum.fromString(update.getPriority()));
    }
    // Tags are not updated here

    return storedTask;
  }

  private static LocalDateTime toLocalDateTime(String date) {
    if (date == null) {
      return null;
    }
    return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
  }

}
