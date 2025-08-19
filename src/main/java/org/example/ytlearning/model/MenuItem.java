package org.example.ytlearning.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(name = "tbl_menu_item")
public class MenuItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String description;
    private Double price;
    private Integer availability;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;




}
