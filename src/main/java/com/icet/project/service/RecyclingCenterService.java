package com.icet.project.service;

import com.icet.project.model.entity.Recycling_CenterEntity;
import com.icet.project.repository.RecyclingCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingCenterService {
    @Autowired
    RecyclingCenterRepository recyclingCenterRepository;

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
        Optional<Recycling_CenterEntity> existing = recyclingCenterRepository.findById(id);
        if (existing.isPresent()) {
            Recycling_CenterEntity c = existing.get();
            c.setLocation(center.getLocation());
            c.setCenterName(center.getCenterName());
            c.setContactNo(center.getContactNo());
            c.setContactPerson(center.getContactPerson());
            c.setEmail(center.getEmail());
            return recyclingCenterRepository.save(c);
        }
        return null;
    }

    public void deleteCenter(Long id) {
        recyclingCenterRepository.deleteById(id);
    }
}