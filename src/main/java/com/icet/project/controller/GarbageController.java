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
import java.util.Map;

@RestController
@RequestMapping("/garbage")
@RequiredArgsConstructor
@CrossOrigin
public class GarbageController {

    final GarbageService garbageService;


    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addGarbage(
            @RequestParam("userName") String userName,
            @RequestParam("title") String title,
            @RequestParam("category") Category category,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "points", required = false) Integer points,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "weight", required = false) Double weight,
            @RequestParam(value = "description", required = false) String description) {

        Garbage_DetailsDTO garbageDTO = new Garbage_DetailsDTO();
        garbageDTO.setUserName(userName);
        garbageDTO.setTitle(title);
        garbageDTO.setCategory(category);
        garbageDTO.setSubmissionDate(new java.util.Date());
        garbageDTO.setPoints(points);
        garbageDTO.setLocation(location);
        garbageDTO.setWeight(weight);
        garbageDTO.setDescription(description);
        garbageDTO.setStatus(Status.PENDING);
        garbageDTO.setAgentID(null);

        garbageService.addGarbageWithImage(garbageDTO, image);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addGarbageJson(@RequestBody Garbage_DetailsDTO garbageDTO) {
        if (garbageDTO.getSubmissionDate() == null) {
            garbageDTO.setSubmissionDate(new java.util.Date());
        }
        garbageDTO.setStatus(Status.PENDING);
        garbageService.addGarbageWithImage(garbageDTO, null);
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

    @GetMapping("/GetCount/Garbages")
    public Long GetAllGarbageCount() {
        return garbageService.getAllGarbage().stream().count();
    }

    @GetMapping("/GetCount/GarbagesKG")
    public Long GetAllGarbageCountKG() {
        return garbageService.getAllGarbage().stream().mapToLong(g -> g.getWeight().longValue()).sum();
    }

    @GetMapping("/GetCount/Pending")
    public Long GetAllPendingGarbageCount() {
        return garbageService.getGarbageByStatus(Status.PENDING).stream().count();
    }

    @GetMapping("/latest")
    public List<Garbage_DetailsEntity> getLatestGarbage(@RequestParam(defaultValue = "5") int limit) {
        return garbageService.getLatestGarbage(limit);
    }

    @PutMapping("/updateStatus/{id}")
    public void updateGarbageStatus(@PathVariable Long id, @RequestParam Status status) {
        garbageService.UpdateGarbageStatus(id, status);
    }

    @GetMapping("/byStatus/{status}")
    public List<Garbage_DetailsEntity> getGarbageByStatus(@PathVariable Status status) {
        return garbageService.getGarbageByStatus(status);
    }

    @GetMapping("/countByStatus")
    public Map<Status, Long> getCountByStatus() {
        return garbageService.getCountByStatus();
    }

    @PutMapping("/{id}/approve")
    public void approveGarbage(@PathVariable Long id) {
        garbageService.UpdateGarbageStatus(id, Status.APPROVED);
    }

    @PutMapping("/{id}/reject")
    public void rejectGarbage(@PathVariable Long id) {
        garbageService.UpdateGarbageStatus(id, Status.REJECTED);
    }

    @PutMapping("/{id}/markInProgress")
    public void markGarbageInProgress(@PathVariable Long id) {
        garbageService.UpdateGarbageStatus(id, Status.IN_PROGRESS);
    }

    @PutMapping("/{id}/complete")
    public void completeGarbage(@PathVariable Long id) {
        garbageService.UpdateGarbageStatus(id, Status.COMPLETED);
    }

    @GetMapping("/Garbage/SearchBy/{userName}")
    public List<Garbage_DetailsEntity> getGarbageByUserName(@PathVariable String userName) {
        return garbageService.getGarbageByUserName(userName);
    }
}