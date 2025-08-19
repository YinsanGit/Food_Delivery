package org.example.ytlearning.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BaseEntity {

    @Column(name = "created_by")
    private String createBy;
    @Column(name = "created_at" , updatable = false)
    private Date createAt;
    @Column(name = "updated_by")
    private String updateBy;
    @Column(name = "updated_at")
    private Date updateAt;
}
