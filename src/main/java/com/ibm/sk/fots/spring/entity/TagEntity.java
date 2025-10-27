package com.ibm.sk.fots.spring.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TAG")
@Data
@NoArgsConstructor
public class TagEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private Long tagId;

  @Column(unique = true)
  private String name;

  @Column
  private boolean active;

}
