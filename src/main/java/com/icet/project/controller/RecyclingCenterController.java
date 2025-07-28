package com.icet.project.controller;

import com.icet.project.model.dto.Recycling_CenterDTO;
import com.icet.project.model.entity.Recycling_CenterEntity;
import com.icet.project.service.Recyclecenter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recycling-centers")
@RequiredArgsConstructor
public class RecyclingCenterController {

    final Recyclecenter recyclingCenterService;

    @GetMapping("/getAll")
    public List<Recycling_CenterEntity> getAllCenters() {
        return recyclingCenterService.getAllCenters();
    }

    @GetMapping("/search/{centerName}")
    public Recycling_CenterEntity getCenterByName(@PathVariable String centerName) {
        return recyclingCenterService.getCenterByName(centerName);
    }

    @PostMapping("add")
    public Recycling_CenterEntity createCenter(@RequestBody Recycling_CenterEntity center) {
        return recyclingCenterService.createCenter(center);
    }

    @PutMapping("/{id}")
    public Recycling_CenterEntity updateCenter(@PathVariable Long id, @RequestBody Recycling_CenterEntity center) {
        return recyclingCenterService.updateCenter(id, center);
    }

    @DeleteMapping("/{id}")
    public void deleteCenter(@PathVariable Long id) {
        recyclingCenterService.deleteCenter(id);
    }

    @GetMapping("/search/by-location/{location}")
    public List<Recycling_CenterDTO> getAllLocationcenters(@PathVariable String location ){
        return recyclingCenterService.getAllLocationcenters(location);
    }
}