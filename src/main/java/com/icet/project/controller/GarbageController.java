package com.icet.project.controller;

import com.icet.project.model.entity.Garbage_DetailsEntity;
import com.icet.project.service.GarbageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garbage")
public class GarbageController {
    @Autowired
    GarbageService garbageService;

    @GetMapping("/getAll")
    public List<Garbage_DetailsEntity> getAllGarbage() {
        return garbageService.getAllGarbage();
    }

    @GetMapping("/search/{title}")
    public Garbage_DetailsEntity getGarbageById(@PathVariable String title) {
        return garbageService.getGarbageById(title);
    }

    @PostMapping("/add")
    public Garbage_DetailsEntity createGarbage(@RequestBody Garbage_DetailsEntity garbage) {
        return garbageService.createGarbage(garbage);
    }

    @PutMapping("/update{id}")
    public Garbage_DetailsEntity updateGarbage(@PathVariable Long id) {
        return garbageService.updateGarbage(id);
    }

    @DeleteMapping("/delete{id}")
    public void deleteGarbage(@PathVariable Long id) {
        garbageService.deleteGarbage(id);
    }
}