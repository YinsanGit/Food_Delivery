package org.example.ytlearning.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.ytlearning.emunration.VehicleType;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_delivery_partner")
public class DeliveryPartner extends BaseEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String firstName;
    private String lastName;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    private String password;
    private String gender;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;
    private String email;
    private String address;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private boolean available;

}