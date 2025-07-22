package com.icet.project.service;

import com.icet.project.model.dto.Garbage_DetailsDTO;
import com.icet.project.model.entity.Garbage_DetailsEntity;

import java.util.List;

public interface GarbageService {
    void addGarbage(Garbage_DetailsDTO garbageDetails);
    List<Garbage_DetailsEntity> getAllGarbage();
    List<Garbage_DetailsEntity> searchGarbage(String title);
    void deleteGarbage(Long id);
}
