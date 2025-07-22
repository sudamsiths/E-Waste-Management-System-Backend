package com.icet.project.service.impl;

import com.icet.project.model.entity.FeedbackEntity;
import com.icet.project.repository.FeedbackRepository;
import com.icet.project.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor

public class FeedbackServiceImpl implements FeedbackService {
    final FeedbackRepository feedbackRepository;


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