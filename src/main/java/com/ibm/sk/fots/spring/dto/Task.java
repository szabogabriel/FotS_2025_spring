package com.ibm.sk.fots.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
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
