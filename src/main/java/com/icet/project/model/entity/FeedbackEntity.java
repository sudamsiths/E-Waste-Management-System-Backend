package com.icet.project.model.entity;

import com.icet.project.utill.FeedbackType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="feedback")
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long feedbackId;
    private String customerName;
    private String email;
    private Date submittedAt;
    @Enumerated(EnumType.STRING)
    private FeedbackType feedbackType;
    private String message;

}
