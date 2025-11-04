package com.omniserve.commondblib.entity;

import com.omniserve.commondblib.state.OrderState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SupplyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String supplyId;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String supplierId;

    @CreationTimestamp
    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime ;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState orderState;
}
