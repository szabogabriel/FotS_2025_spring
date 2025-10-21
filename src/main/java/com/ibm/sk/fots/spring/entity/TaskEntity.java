package com.ibm.sk.fots.spring.entity;

import java.time.LocalDateTime;
import java.util.List;

public class TaskEntity {

  private Long id;

  private Long taskId;

  private String title;

  private String description;

  private boolean completed;

  private LocalDateTime dueDate;

  private Integer priority;

  private Long owner;

  private List<TagEntity> tags;

  public TaskEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public LocalDateTime getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Long getOwner() {
    return owner;
  }

  public void setOwner(Long owner) {
    this.owner = owner;
  }

  public List<TagEntity> getTags() {
    return tags;
  }

  public void setTags(List<TagEntity> tags) {
    this.tags = tags;
  }
}
