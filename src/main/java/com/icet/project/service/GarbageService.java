package com.icet.project.service;

import com.icet.project.model.dto.Garbage_DetailsDTO;
import com.icet.project.model.entity.Garbage_DetailsEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GarbageService {

    void addGarbage(Garbage_DetailsDTO garbageDetails);
    void addGarbageWithImage(Garbage_DetailsDTO garbageDetails, MultipartFile imageFile);
    List<Garbage_DetailsEntity> getAllGarbage();
    List<Garbage_DetailsEntity> searchGarbage(String title);
    void deleteGarbage(Long id);
    List<Garbage_DetailsDTO> findAllByCategory(String category);

    Garbage_DetailsEntity getGarbageById(Long id);
}
