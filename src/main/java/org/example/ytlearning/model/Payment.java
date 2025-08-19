package org.example.ytlearning.model;
import jakarta.persistence.*;
import lombok.*;
import org.example.ytlearning.emunration.PaymentMethod;
import org.example.ytlearning.emunration.PaymentStatus;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "tbl_payment")
public class Payment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String description;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private Long orderId;




}
