package com.icet.project.controller;

import com.icet.project.model.dto.Garbage_DetailsDTO;
import com.icet.project.model.entity.Garbage_DetailsEntity;
import com.icet.project.service.GarbageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garbage")
@RequiredArgsConstructor
public class GarbageController {

    final GarbageService garbageService;

    @PostMapping("/add")
    public void addGarbage(@RequestBody Garbage_DetailsDTO garbageDTO) {
        garbageService.addGarbage(garbageDTO);
    }
    @GetMapping("/getAll")
    public List<Garbage_DetailsEntity> getAllGarbage() {
        return garbageService.getAllGarbage();
    }
    @GetMapping("/search/{title}")
    public List<Garbage_DetailsEntity> searchGarbage(@PathVariable String title) {
        return garbageService.searchGarbage(title);
    }
    @DeleteMapping("/delete/{title}")
    public void deleteGarbage(@PathVariable Long id) {
        garbageService.deleteGarbage(id);
    }

}