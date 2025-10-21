package com.ibm.sk.fots.spring.dto;

public class Tag {
  private Long tagId;
  private String name;

  public Long getTagId() {
    return tagId;
  }

  public void setTagId(Long tagId) {
    this.tagId = tagId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
