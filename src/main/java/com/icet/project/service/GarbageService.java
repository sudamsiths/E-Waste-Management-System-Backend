package com.icet.project.service;

import com.icet.project.model.dto.Garbage_DetailsDTO;
import com.icet.project.model.entity.Garbage_DetailsEntity;
import com.icet.project.utill.Status;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface GarbageService {

    void addGarbage(Garbage_DetailsDTO garbageDetails);
    void addGarbageWithImage(Garbage_DetailsDTO garbageDetails, MultipartFile imageFile);
    List<Garbage_DetailsEntity> getAllGarbage();
    List<Garbage_DetailsEntity> searchGarbage(String title);
    void deleteGarbage(Long id);
    List<Garbage_DetailsDTO> findAllByCategory(String category);
    Garbage_DetailsEntity getGarbageById(Long id);
    List<Garbage_DetailsEntity> getLatestGarbage(int limit);
    void UpdateGarbageStatus(Long id, Status status);
    List<Garbage_DetailsEntity> getGarbageByStatus(Status status);
    Map<Status, Long> getCountByStatus();

    List<Garbage_DetailsEntity> getGarbageByUserName(String userName);
}
