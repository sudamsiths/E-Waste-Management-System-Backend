package com.icet.project.repository;

import com.icet.project.model.entity.Recycling_CenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecyclingCenterRepository extends JpaRepository<Recycling_CenterEntity , Long> {

    Optional<Object> findBycenterName(String centerName);
    List<Recycling_CenterEntity> findByLocation(String location);
    Optional<Object> findByEmail(String email);
}
