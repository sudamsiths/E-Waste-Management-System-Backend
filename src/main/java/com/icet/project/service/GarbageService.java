package com.icet.project.service;

import com.icet.project.model.entity.Garbage_DetailsEntity;
import com.icet.project.repository.GarbageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GarbageService {
    @Autowired
    private GarbageRepository garbageRepository;

    public List<Garbage_DetailsEntity> getAllGarbage() {
        return garbageRepository.findAll();
    }

    public Garbage_DetailsEntity getGarbageById(String title) {
        Optional<Garbage_DetailsEntity> byName = garbageRepository.findBytitle(title);
        if (byName.isPresent()) {
            return byName.get();
        }
        return null;
    }

    public Garbage_DetailsEntity createGarbage(Garbage_DetailsEntity garbage) {
        return garbageRepository.save(garbage);
    }

    public Garbage_DetailsEntity updateGarbage( Long id) {
        Optional<Garbage_DetailsEntity> garbageOptional = garbageRepository.findById(id);
        if (garbageOptional.isPresent()) {
            Garbage_DetailsEntity garbage = garbageOptional.get();
            return garbageRepository.save(garbage);
        }
        return null;
    }

    public void deleteGarbage(Long id) {
        garbageRepository.deleteById(id);
    }
}