package org.example.ytlearning.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(name = "tbl_feedback")
public class FeedBack extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200)
    private String comment;
    private double rating;
    private Long feedBackDate;
    private Long userId;
    private Long restaurantId;
    private Long orderId;
    private Long diliveryId;

}
