package com.omniserve.commondblib.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentInfo {
    @Id
    private String shipmentId;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String shipperId;

    @CreationTimestamp
    private LocalDateTime checkInTime;

    private LocalDateTime DeliveredTime;

    @Column(nullable = false)
    private Double pickupLongitude;

    @Column(nullable = false)
    private Double pickupLatitude;

    @Column(nullable = false)
    private Double dropLatitude;

    @Column(nullable = false)
    private Double dropLongitude;
}
