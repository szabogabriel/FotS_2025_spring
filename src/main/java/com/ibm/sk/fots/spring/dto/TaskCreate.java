package com.ibm.sk.fots.spring.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskCreate {
  private String title;
  private String description;
  private boolean completed;
  private String dueDate; // ISO 8601 format
  private String priority;
  private java.util.List<String> tags;

}
