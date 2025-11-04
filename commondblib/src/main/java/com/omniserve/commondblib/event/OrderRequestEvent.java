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
public class OrderRequestEvent {
    private String orderId;
    private String userId;
    private String itemId;
    private Long orderQuantity;
    private OrderState orderState;
    private LocalDateTime timestamp;
}

