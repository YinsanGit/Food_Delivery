package org.example.ytlearning.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tbl_user")
public class User extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //unique = true it's mean that username can't duplicate
//    @Column(name = "username" , nullable = false, unique = true, length = 50)
    private String username;
    private String firstname;
    private String lastname;
    @Column(length = 10)
    private String gender;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String email;
//    @Column(name = "phone_number" , unique = true, nullable = false)
    private String phoneNumber;
    private String address;
    @Column(name = "user_type")
    private String UserType;
    private String status;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Device> devices;

}
