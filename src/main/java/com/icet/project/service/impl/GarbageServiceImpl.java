package com.icet.project.service.impl;

import com.icet.project.model.dto.Garbage_DetailsDTO;
import com.icet.project.model.entity.Garbage_DetailsEntity;
import com.icet.project.repository.GarbageRepository;
import com.icet.project.service.GarbageService;
import com.icet.project.utill.Category;
import com.icet.project.utill.Status;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GarbageServiceImpl implements GarbageService {

    final GarbageRepository garbageRepository;
    final ModelMapper modelMapper;

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    @Override
    public void addGarbage(Garbage_DetailsDTO garbageDetails) {
        Garbage_DetailsEntity garbageEntity = modelMapper.map(garbageDetails, Garbage_DetailsEntity.class);
        if (garbageEntity.getStatus() == null) {
            garbageEntity.setStatus(Status.PENDING);
        }
        if (garbageEntity.getSubmissionDate() == null) {
            garbageEntity.setSubmissionDate(new java.util.Date());
        }

        garbageRepository.save(garbageEntity);
    }

    @Override
    public void addGarbageWithImage(Garbage_DetailsDTO garbageDetails, MultipartFile imageFile) {
        try {
            String fileName = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                // Create the upload directory if it doesn't exist
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Generate a unique file name
                String originalFileName = imageFile.getOriginalFilename();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                fileName = UUID.randomUUID() + fileExtension;

                // Save the file
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath);

                // Set the image path in the DTO
                garbageDetails.setImage(fileName);
            }

            // Set default status if null
            if (garbageDetails.getStatus() == null) {
                garbageDetails.setStatus(Status.PENDING);
            }

            // Save the garbage details with the image path
            Garbage_DetailsEntity garbageEntity = modelMapper.map(garbageDetails, Garbage_DetailsEntity.class);
            garbageRepository.save(garbageEntity);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public List<Garbage_DetailsEntity> getAllGarbage() {
        return garbageRepository.findAll();
    }

    @Override
    public List<Garbage_DetailsEntity> searchGarbage(String title) {
        return garbageRepository.findByTitle(title);
    }

    @Override
    public void deleteGarbage(Long id) {
        if (garbageRepository.existsById(id)) {
            garbageRepository.deleteById(id);
        } else {
            throw new RuntimeException("Garbage with id " + id + " does not exist.");
        }
    }

    @Override
    public List<Garbage_DetailsDTO> findAllByCategory(String category) {
        Category value = Category.valueOf(category.toUpperCase());
        List<Garbage_DetailsEntity> users = garbageRepository.findByCategory(value);// custom method Find agent by role
        return users.stream()
                .map(entity -> modelMapper.map(entity, Garbage_DetailsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Garbage_DetailsEntity getGarbageById(Long id) {
        return garbageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garbage with id " + id + " does not exist."));
    }

    @Override
    public List<Garbage_DetailsEntity> getLatestGarbage(int limit) {
        return garbageRepository.findAll(PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }

    @Override
    public void UpdateGarbageStatus(Long id, Status status) {
        Garbage_DetailsEntity garbage = garbageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garbage with id " + id + " does not exist."));
        garbage.setStatus(status);
        garbageRepository.save(garbage);
    }

    @Override
    public List<Garbage_DetailsEntity> getGarbageByStatus(Status status) {
        // Using stream to filter by status
        return garbageRepository.findAll().stream()
                .filter(garbage -> garbage.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Status, Long> getCountByStatus() {
        Map<Status, Long> counts = new EnumMap<>(Status.class);
        // Initialize all status counts to 0
        for (Status status : Status.values()) {
            counts.put(status, 0L);
        }

        // Count items by status
        List<Garbage_DetailsEntity> allGarbage = garbageRepository.findAll();
        for (Garbage_DetailsEntity garbage : allGarbage) {
            Status status = garbage.getStatus();
            if (status != null) {
                counts.put(status, counts.getOrDefault(status, 0L) + 1);
            }
        }

        return counts;
    }

    @Override
    public List<Garbage_DetailsEntity> getGarbageByUserName(String userName) {
        return garbageRepository.findByUserName(userName);
    }

    @Override
    public void UpdateGarbageDetails(Long id, Garbage_DetailsDTO garbageDetailsDTO) {
        garbageRepository.findById(id);
        Garbage_DetailsEntity updatedGarbage = modelMapper.map(garbageDetailsDTO, Garbage_DetailsEntity.class);
        updatedGarbage.setId(id);
        garbageRepository.save(updatedGarbage);
    }

    @Override
    public void assignAgentToGarbage(Long id, String agentName) {
        Garbage_DetailsEntity garbage = garbageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garbage with id " + id + " does not exist."));
        garbage.setAgentName(agentName);
        garbageRepository.save(garbage);
    }
}