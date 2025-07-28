package com.icet.project.service.impl;

import com.icet.project.model.dto.Recycling_CenterDTO;
import com.icet.project.model.entity.Recycling_CenterEntity;
import com.icet.project.repository.RecyclingCenterRepository;
import com.icet.project.service.Recyclecenter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RecyclingCenterServiceImpl implements Recyclecenter {
    final RecyclingCenterRepository recyclingCenterRepository;

    final ModelMapper modelMapper;

    public List<Recycling_CenterEntity> getAllCenters() {
        return recyclingCenterRepository.findAll();
    }

    public Recycling_CenterEntity getCenterByName(String centerName) {
        Optional<Object> bycenterName = recyclingCenterRepository.findBycenterName(centerName);
        if (bycenterName.isPresent()) {
            return (Recycling_CenterEntity) bycenterName.get();
        }
        return null;
    }

    public Recycling_CenterEntity createCenter(Recycling_CenterEntity center) {
        if (recyclingCenterRepository.findBycenterName(center.getCenterName()).isPresent()) {
            throw new RuntimeException("Recycling Center with name " + center.getCenterName() + " already exists.");
        }
        if (center.getEmail() != null && recyclingCenterRepository.findByEmail(center.getEmail()).isPresent()) {
            throw new RuntimeException("Recycling Center with email " + center.getEmail() + " already exists.");
        }
        return recyclingCenterRepository.save(center);
    }

    public Recycling_CenterEntity updateCenter(Long id, Recycling_CenterEntity center) {
        if (recyclingCenterRepository.existsById(id)) {
            center.setCenterId(id);
            return recyclingCenterRepository.save(center);
        } else {
            throw new RuntimeException("Recycling Center with id " + id + " does not exist.");
        }
    }

    public void deleteCenter(Long id) {
        recyclingCenterRepository.deleteById(id);
    }

    @Override
    public List<Recycling_CenterDTO> getAllLocationcenters(String location) {
       List<Recycling_CenterEntity> recyclingCenterEntities =recyclingCenterRepository.findByLocation(location);
        return recyclingCenterEntities.stream()
                .map(entity -> modelMapper.map(entity, Recycling_CenterDTO.class))
                .toList();
    }
}