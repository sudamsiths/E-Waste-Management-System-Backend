package com.icet.project.service;

import com.icet.project.model.dto.FeedbackDTO;
import com.icet.project.model.entity.FeedbackEntity;

import java.util.List;

public interface FeedbackService {
    List<FeedbackEntity> getAllFeedback();
    FeedbackEntity getFeedbackById(Long id);
    FeedbackEntity createFeedback(FeedbackEntity feedback);
    void deleteFeedback(Long id);
    List<FeedbackDTO> getFeedbackByType(String feedbackType);
}
