package com.icet.project.service;

import com.icet.project.model.entity.Recycling_CenterEntity;
import com.icet.project.repository.RecyclingCenterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingCenterServiceImpl implements Recyclecenter{
    @Autowired
    RecyclingCenterRepository recyclingCenterRepository;

    ModelMapper modelMapper = new ModelMapper();

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
}