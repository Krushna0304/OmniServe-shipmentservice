package com.omniserve.commondblib.event;

import com.omniserve.commondblib.state.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ShipmentRequestEvent {
        private String shipmentId;
        private String orderId;
        private Double pickupLongitude;
        private Double pickupLatitude;
        private Double dropLongitude;
        private Double dropLatitude;
        private OrderState orderState;
        private String userId;
        private LocalDateTime timestamp;
}
