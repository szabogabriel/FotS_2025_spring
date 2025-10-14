package com.ibm.sk.fots.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long taskId;
    private String title;
    private String description;
    private boolean completed;
}
