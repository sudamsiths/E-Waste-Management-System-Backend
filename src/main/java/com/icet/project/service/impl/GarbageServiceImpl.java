package com.icet.project.service.impl;

import com.icet.project.model.dto.Garbage_DetailsDTO;
import com.icet.project.model.entity.Garbage_DetailsEntity;
import com.icet.project.repository.GarbageRepository;
import com.icet.project.service.GarbageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GarbageServiceImpl implements GarbageService {

    final GarbageRepository garbageRepository;

     ModelMapper modelMapper = new ModelMapper();

    @Override
    public void addGarbage(Garbage_DetailsDTO garbageDetails) {
        Garbage_DetailsEntity garbageEntity = modelMapper.map(garbageDetails, Garbage_DetailsEntity.class);
        garbageRepository.save(garbageEntity);
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
}