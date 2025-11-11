package com.omniserve.commondblib.util;

import com.omniserve.commondblib.dto.ItemDto;
import com.omniserve.commondblib.entity.Item;
import com.omniserve.commondblib.entity.OrderRequest;
import com.omniserve.commondblib.entity.ShipmentInfo;
import com.omniserve.commondblib.entity.ShipmentRequest;
import com.omniserve.commondblib.event.OrderRequestEvent;
import com.omniserve.commondblib.event.ShipmentRequestEvent;

import java.time.LocalDateTime;

public class Mapper {

    private Mapper(){}

    public static ItemDto toDto(Item item) {
        if (item == null) {
            return null;
        }

        return new ItemDto(
                item.getItemId(),
                item.getItemName(),
                item.getItemDescription(),
                item.getItemPrice(),
                item.getItemImgUrl()
        );
    }
    public static OrderRequestEvent toDto(OrderRequest order) {
        if (order == null) {
            return null;
        }

        return OrderRequestEvent.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .itemId(order.getItemId())
                .orderQuantity(order.getOrderQuantity())
                .orderState(order.getOrderState())
                .timestamp(order.getTimestamp())
                .build();
    }

    public static OrderRequest toEntity(OrderRequestEvent dto) {
        if (dto == null) {
            return null;
        }

        return OrderRequest.builder()
                .orderId(dto.getOrderId())
                .userId(dto.getUserId())
                .itemId(dto.getItemId())
                .orderQuantity(dto.getOrderQuantity())
                .orderState(dto.getOrderState())
                .timestamp(dto.getTimestamp())
                .build();
    }
    public static ShipmentInfo toEntity(ShipmentRequest shipmentRequest){
        return ShipmentInfo.builder()
                .shipmentId(shipmentRequest.getShipmentId())
                .orderId(shipmentRequest.getOrderId())
                .userId(shipmentRequest.getUserId())
                .shipperId(ApplicationContextUtil.getLoggedUser())
                .checkInTime(LocalDateTime.now())
                .pickupLongitude(shipmentRequest.getPickupLongitude())
                .pickupLatitude(shipmentRequest.getPickupLatitude())
                .dropLatitude(shipmentRequest.getDropLatitude())
                .dropLongitude(shipmentRequest.getDropLongitude())
                .build();

    }

    public static  ShipmentRequestEvent toDto(ShipmentRequest shipment)
    {
        return new ShipmentRequestEvent(
                shipment.getShipmentId(),
                shipment.getOrderId(),
                shipment.getPickupLongitude(),
                shipment.getPickupLatitude(),
                shipment.getDropLongitude(),
                shipment.getDropLatitude(),
                shipment.getOrderState(), // Assuming initial shipment state is PENDING
                shipment.getUserId(),
                shipment.getTimestamp()
        );
    }
}
