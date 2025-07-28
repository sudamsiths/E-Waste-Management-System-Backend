package com.icet.project.model.dto;

import com.icet.project.utill.FeedbackType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeedbackDTO {
    private Long feedbackId;
    private String customerName;
    private String email;
    private String submittedAt;
    private FeedbackType feedbackType;
    private String message;
}
