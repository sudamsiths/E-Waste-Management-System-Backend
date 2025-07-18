package com.icet.project.repository;

import com.icet.project.model.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository <FeedbackEntity , Long> {
}
