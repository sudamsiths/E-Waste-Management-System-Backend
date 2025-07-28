package com.icet.project.controller;

import com.icet.project.model.dto.FeedbackDTO;
import com.icet.project.model.entity.FeedbackEntity;
import com.icet.project.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor

public class FeedbackController {

    final FeedbackService feedbackService;

    @GetMapping("/getAll")
    public List<FeedbackEntity> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @GetMapping("/search{id}")
    public FeedbackEntity getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }

    @PostMapping("/add")
    public FeedbackEntity createFeedback(@RequestBody FeedbackEntity feedback) {
        return feedbackService.createFeedback(feedback);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }

    @GetMapping("/search/By-FeedbackType/{feedbackType}")
    public List<FeedbackDTO> getTypeFeedback(@PathVariable("feedbackType") String feedbackType ){
        return feedbackService.getFeedbackByType(feedbackType);
    }
}