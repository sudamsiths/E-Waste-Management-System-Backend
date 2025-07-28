package com.icet.project.service.impl;

import com.icet.project.model.dto.FeedbackDTO;
import com.icet.project.model.entity.FeedbackEntity;
import com.icet.project.repository.FeedbackRepository;
import com.icet.project.service.FeedbackService;
import com.icet.project.utill.FeedbackType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class FeedbackServiceImpl implements FeedbackService {


    final FeedbackRepository feedbackRepository;
    final ModelMapper modelMapper ;


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

    @Override
    public List<FeedbackDTO> getFeedbackByType(String feedbackType) {
        FeedbackType value = FeedbackType.valueOf(feedbackType.toUpperCase());
        List<FeedbackEntity> type = feedbackRepository.findByFeedbackType(value);// custom method Find feedback by type
        return type.stream()
                .map(entity -> modelMapper.map(entity, FeedbackDTO.class))
                .collect(Collectors.toList());
    }
}