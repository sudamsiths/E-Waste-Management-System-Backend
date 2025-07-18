package com.icet.project.service;

import com.icet.project.model.entity.FeedbackEntity;
import com.icet.project.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;


    public List<FeedbackEntity> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public FeedbackEntity getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public FeedbackEntity createFeedback(FeedbackEntity feedback) {
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}