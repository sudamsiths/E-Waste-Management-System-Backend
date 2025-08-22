package com.icet.project.service.impl;

import com.icet.project.model.dto.Garbage_DetailsDTO;
import com.icet.project.model.entity.Garbage_DetailsEntity;
import com.icet.project.repository.GarbageRepository;
import com.icet.project.service.GarbageService;
import com.icet.project.utill.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
}