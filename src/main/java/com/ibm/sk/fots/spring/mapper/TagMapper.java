package com.ibm.sk.fots.spring.mapper;

import com.ibm.sk.fots.spring.dto.Tag;
import com.ibm.sk.fots.spring.entity.TagEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TagMapper {

  public static Tag toDto(TagEntity entity) {
    if (entity == null) {
      return null;
    }

    Tag dto = new Tag();
    dto.setTagId(entity.getTagId());
    dto.setName(entity.getName());

    return dto;
  }

  public static List<Tag> toDtoList(List<TagEntity> entities) {
    if (entities == null) {
      return null;
    }
    return entities.stream()
        .map(TagMapper::toDto)
        .collect(Collectors.toList());
  }

  public static List<String> toDtoStringList(List<TagEntity> entities) {
    if (entities == null) {
      return null;
    }
    return entities.stream()
        .map(TagEntity::getName)
        .collect(Collectors.toList());
  }

  public static TagEntity toNewEntity(Tag dto) {
    if (dto == null) {
      return null;
    }

    TagEntity entity = new TagEntity();
    entity.setName(dto.getName());
    entity.setActive(true);
    return entity;
  }

  public static TagEntity toNewEntity(String name) {
    if (name == null) {
      return null;
    }

    TagEntity entity = new TagEntity();
    entity.setName(name);
    entity.setActive(true);
    return entity;
  }
}
