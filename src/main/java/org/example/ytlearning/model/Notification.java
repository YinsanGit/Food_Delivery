package org.example.ytlearning.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.ytlearning.emunration.Notificaition_Chanel;
import org.example.ytlearning.emunration.Notification_Type;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tbl_notification")
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private Notification_Type notificationType;

    @Enumerated(EnumType.STRING)
    private Notificaition_Chanel notificationChannel;

    private boolean read;
    private Long userId;
    private Long deviceId;



}
