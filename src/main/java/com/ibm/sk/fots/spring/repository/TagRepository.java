package com.ibm.sk.fots.spring.repository;

import com.ibm.sk.fots.spring.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

  Optional<TagEntity> findByTagId(Long tagId);

  Optional<TagEntity> findByName(String name);

}
