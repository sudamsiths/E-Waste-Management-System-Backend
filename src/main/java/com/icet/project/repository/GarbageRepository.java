package com.icet.project.repository;

import com.icet.project.model.dto.Garbage_DetailsDTO;
import com.icet.project.model.entity.Garbage_DetailsEntity;
import com.icet.project.utill.Category;
import com.icet.project.utill.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GarbageRepository extends JpaRepository<Garbage_DetailsEntity,Long> {
    List<Garbage_DetailsEntity> findByTitle(String title);

    List<Garbage_DetailsEntity> findByCategory(Category value);

    List<Garbage_DetailsEntity> findByStatus(Status status);
}