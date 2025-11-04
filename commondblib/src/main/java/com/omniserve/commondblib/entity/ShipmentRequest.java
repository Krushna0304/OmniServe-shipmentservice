package com.omniserve.commondblib.entity;

import com.omniserve.commondblib.state.OrderState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String shipmentId;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private Double pickupLongitude;

    @Column(nullable = false)
    private Double pickupLatitude;

    @Column(nullable = false)
    private Double dropLongitude;

    @Column(nullable = false)
    private Double dropLatitude;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Column(nullable = false)
    private String userId;

    @CreationTimestamp
    private LocalDateTime timestamp;
}
