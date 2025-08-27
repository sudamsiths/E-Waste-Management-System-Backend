package com.icet.project.controller;

import com.icet.project.model.dto.Garbage_DetailsDTO;
import com.icet.project.model.entity.Garbage_DetailsEntity;
import com.icet.project.service.GarbageService;
import com.icet.project.utill.Category;
import com.icet.project.utill.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/garbage")
@RequiredArgsConstructor
@CrossOrigin
public class GarbageController {

    final GarbageService garbageService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addGarbage(
            @RequestParam("title") String title,
            @RequestParam("category") Category category,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "points", required = false) Integer points,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "weight", required = false) Double weight,
            @RequestParam(value = "description", required = false) String description) {

        Garbage_DetailsDTO garbageDTO = new Garbage_DetailsDTO();
        garbageDTO.setTitle(title);
        garbageDTO.setCategory(category);
        garbageDTO.setPoints(points);
        garbageDTO.setLocation(location);
        garbageDTO.setWeight(weight);
        garbageDTO.setDescription(description);
        garbageDTO.setStatus(Status.PENDING); // Set default status explicitly

        garbageService.addGarbageWithImage(garbageDTO, image);
    }

    @GetMapping("/getAll")
    public List<Garbage_DetailsEntity> getAllGarbage() {
        return garbageService.getAllGarbage();
    }

    @GetMapping("/search/{title}")
    public List<Garbage_DetailsEntity> searchGarbage(@PathVariable String title) {
        return garbageService.searchGarbage(title);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGarbage(@PathVariable Long id) {
        garbageService.deleteGarbage(id);
    }

    @GetMapping("/search/By-Category/{category}")
    public List<Garbage_DetailsDTO> findAllByCategory(@PathVariable("category") String category) {
        return garbageService.findAllByCategory(category);
    }

    @GetMapping("/getById/{id}")
    public Garbage_DetailsEntity getGarbageById(@PathVariable Long id) {
        return garbageService.getGarbageById(id);
    }

}