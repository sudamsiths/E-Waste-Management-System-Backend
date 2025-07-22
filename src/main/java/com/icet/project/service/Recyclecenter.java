package com.icet.project.service;

import com.icet.project.model.entity.Recycling_CenterEntity;

import java.util.List;

public interface Recyclecenter {
    List<Recycling_CenterEntity> getAllCenters();

    Recycling_CenterEntity getCenterByName(String centerName);

    Recycling_CenterEntity createCenter(Recycling_CenterEntity center);

    Recycling_CenterEntity updateCenter(Long id, Recycling_CenterEntity center);

    void deleteCenter(Long id);
}
