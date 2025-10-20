package com.ibm.sk.fots.spring.mapper;

import com.ibm.sk.fots.spring.dto.Tag;

public class TagMapper {

  public static Tag toNew(String name) {
    if (name == null) {
      return null;
    }

    Tag newTag = new Tag();
    newTag.setName(name);
    return newTag;
  }
}
