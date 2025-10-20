package com.ibm.sk.fots.spring.dto;

public enum PriorityEnum {
  LOW("Low"),
  MEDIUM("Medium"),
  HIGH("High"),
  ;

  private String value;

  private PriorityEnum(String value) {
    this.value = value;
  }

  public static PriorityEnum fromString(String value) {
    if (value == null) {
      return null;
    }
    try {
      return PriorityEnum.valueOf(value.toUpperCase());
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
  public Integer toInteger() {
    return this.ordinal();
  }
  public static PriorityEnum fromInteger(int value) {
    if (value < 0 || value >= PriorityEnum.values().length) {
      return null;
    }
    return PriorityEnum.values()[value];
  }
  public String toString() {
    return this.value;
  }
}
