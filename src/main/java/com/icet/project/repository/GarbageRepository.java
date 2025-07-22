package com.icet.project.repository;

import com.icet.project.model.dto.Garbage_DetailsDTO;
import com.icet.project.model.entity.Garbage_DetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GarbageRepository extends JpaRepository<Garbage_DetailsEntity,Long> {
    List<Garbage_DetailsEntity> findByTitle(String title);
}