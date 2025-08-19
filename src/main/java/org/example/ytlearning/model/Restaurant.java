package org.example.ytlearning.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Table(name = "tbl_restaurant")
public class Restaurant extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String category;
    private String description;
    private Double rating;
    private String address;
    private String phoneNumber;
    private String logoUrl;
    @Temporal(TemporalType.TIMESTAMP)
    private Date openTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date closeTime;

}
