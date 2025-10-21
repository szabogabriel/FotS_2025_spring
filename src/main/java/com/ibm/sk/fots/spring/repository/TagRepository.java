package com.ibm.sk.fots.spring.repository;

import com.ibm.sk.fots.spring.entity.TagEntity;
import com.ibm.sk.fots.spring.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TagRepository {

  private AtomicLong latsgeneratedId = new AtomicLong(0);
  private List<TagEntity> storedTagEntityList = new ArrayList<>();

  public Optional<TagEntity> findByTagId(Long tagId) {
    return storedTagEntityList.stream().filter(t -> t.getTagId().equals(tagId)).findFirst();
  }

  public Optional<TagEntity> findByName(String name) {
    return storedTagEntityList.stream().filter(t -> t.getName().equals(name)).findFirst();
  }

  public TagEntity save(TagEntity e) {
    if (e.getId() == null) {
      e.setId(latsgeneratedId.incrementAndGet());
    }
    storedTagEntityList.add(e);

    return e;
  }

  public List<TagEntity> findAll() {
    return new ArrayList<>(storedTagEntityList);
  }

}
