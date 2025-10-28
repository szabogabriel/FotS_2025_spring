package com.ibm.sk.fots.spring.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Task {
  private Long taskId;
  private String title;
  private String description;
  private boolean completed;
  private LocalDateTime dueDate;
  private PriorityEnum priority;
  private List<String> tags;

}
