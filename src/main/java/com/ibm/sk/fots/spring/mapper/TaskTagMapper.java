package com.ibm.sk.fots.spring.mapper;

import com.ibm.sk.fots.spring.dto.*;
import com.ibm.sk.fots.spring.entity.TagEntity;
import com.ibm.sk.fots.spring.entity.TaskEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface TaskTagMapper {

  TaskTagMapper INSTANCE = Mappers.getMapper(TaskTagMapper.class);

  @Mappings({ @Mapping(target = "priority", source = "priority", qualifiedByName = "convertIntegerToPriority"),
      @Mapping(target = "tags", source = "tags", qualifiedByName = "mapTagToString") })
  Task toDto(TaskEntity entity);

  @Mappings({
      @Mapping(target = "priority", source = "priority", qualifiedByName = "convertPriorityStringToInteger", defaultValue = "PriorityEnum.MEDIUM"),
      @Mapping(target = "tags", ignore = true) })
  TaskEntity toNewEntity(TaskCreate dto);

  List<Task> toDtoListTask(List<TaskEntity> entities);

  @Mappings({
      @Mapping(target = "title", source = "update.title", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
      @Mapping(target = "description", source = "update.description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
      @Mapping(target = "completed", source = "update.completed", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
      @Mapping(target = "dueDate", source = "update.dueDate", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
      @Mapping(target = "priority", source = "update.priority", qualifiedByName = "convertPriorityStringToInteger", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
      @Mapping(target = "tags", ignore = true) })
  TaskEntity updateEntity(@MappingTarget TaskEntity entity, TaskUpdate update);

  Tag toDto(TagEntity entity);

  @Mappings({ @Mapping(target = "name", source = "name"), @Mapping(target = "active", constant = "true") })
  TagEntity toNewEntity(String name);

  @Mappings({ @Mapping(target = "name", source = "name"), @Mapping(target = "tagId", ignore = true),
      @Mapping(target = "active", constant = "true") })
  TagEntity toNewEntity(Tag dto);

  List<Tag> toDtoListTag(List<TagEntity> entities);

  @Named(value = "mapTagToString")
  default List<String> mapTagToString(List<TagEntity> entities) {
    if (entities == null) {
      return null;
    }
    return entities.stream().map(TagEntity::getName).collect(Collectors.toList());

  }

  @Named(value = "convertIntegerToPriority")
  default PriorityEnum convertIntegerToPriority(Integer source) {
    return PriorityEnum.fromInteger(source);
  }

  @Named(value = "convertPriorityStringToInteger")
  default Integer convertPriorityStringToInteger(String source) {
    return PriorityEnum.fromString(source) != null
        ? PriorityEnum.fromString(source).toInteger()
        : PriorityEnum.MEDIUM.toInteger();
  }
}
