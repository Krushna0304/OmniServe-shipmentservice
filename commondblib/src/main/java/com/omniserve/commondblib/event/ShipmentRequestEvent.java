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
        String shipmentId;
        String orderId;
        Double pickupLongitude;
        Double pickupLatitude;
        Double dropLongitude;
        Double dropLatitude;
        OrderState orderState;
        String userId;
        LocalDateTime timestamp;

}
