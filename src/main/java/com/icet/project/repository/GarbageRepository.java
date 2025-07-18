package com.icet.project.repository;

import com.icet.project.model.entity.Garbage_DetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GarbageRepository extends JpaRepository<Garbage_DetailsEntity,Long> {
    Optional<Garbage_DetailsEntity> findBytitle(String title);
}
