package com.icet.project.repository;

import com.icet.project.model.dto.FeedbackDTO;
import com.icet.project.model.entity.FeedbackEntity;
import com.icet.project.utill.FeedbackType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository <FeedbackEntity , Long> {

    List<FeedbackEntity> findByFeedbackType(FeedbackType value);
}
