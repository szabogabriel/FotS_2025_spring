package com.ibm.sk.fots.spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TASK")
@Data
@NoArgsConstructor
public class TaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private Long taskId;

  @Column
  private String title;

  @Column
  private String description;

  @Column
  private boolean completed;

  @Column
  private LocalDateTime dueDate;

  @Column
  private Integer priority;

  @Column
  private Long owner;

  @ManyToMany
  @JoinTable(name = "task_tag", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private List<TagEntity> tags;

}
