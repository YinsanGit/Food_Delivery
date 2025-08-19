package org.example.ytlearning.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //unique = true it's mean that username can't duplicate
    @Column(name = "username" , nullable = false, unique = true, length = 50)
    private String username;
    private String firstname;
    private String lastname;
    @Column(length = 10)
    private String gender;
    private Date dateOfBirth;
    private String email;
    @Column(name = "phone_number" , unique = true, nullable = false)
    private String phoneNumber;
    private String address;
    @Column(name = "user_type")
    private String UserType;
    private String status;

}
