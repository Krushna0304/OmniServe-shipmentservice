package com.omniserve.commondblib.util;

import com.omniserve.commondblib.dto.ItemDto;
import com.omniserve.commondblib.event.OrderRequestEvent;
import com.omniserve.commondblib.event.ShipmentRequestEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventLogPrinter {

    private EventLogPrinter(){}

    public static void  logOrder(OrderRequestEvent e) {
        if (e == null) {
            log.warn("⚠️ Received null OrderRequestEvent!");
            return;
        }

        log.info("""
                --- Order Details ---
                Order ID      : {}
                User ID       : {}
                Item ID       : {}
                Quantity      : {}
                Order State   : {}
                Timestamp     : {}
                ---------------------
                """,
                e.getOrderId(),
                e.getUserId(),
                e.getItemId(),
                e.getOrderQuantity(),
                e.getOrderState(),
                e.getTimestamp()
        );
    }

    public static void logShipment(ShipmentRequestEvent e) {
        if (e == null) {
            log.warn("⚠️ Received null ShipmentRequestEvent!");
            return;
        }

        log.info("""
                --- Shipment Details ---
                Shipment ID   : {}
                Order ID      : {}
                User ID       : {}
                Pickup (lat, long) : ({}, {})
                Drop   (lat, long) : ({}, {})
                Order State   : {}
                Timestamp     : {}
                ------------------------
                """,
                e.getShipmentId(),
                e.getOrderId(),
                e.getUserId(),
                e.getPickupLatitude(),
                e.getPickupLongitude(),
                e.getDropLatitude(),
                e.getDropLongitude(),
                e.getOrderState(),
                e.getTimestamp()
        );
    }

    public static void logItem(ItemDto item) {
        if (item == null) {
            log.warn("⚠️ Item is null!");
            return;
        }

        log.info("""
            --- Item Details ---
            ID          : {}
            Name        : {}
            Description : {}
            Price       : {}
            Image URL   : {}
            ---------------------
            """,
                item.getItemId(),
                item.getItemName(),
                item.getItemDescription(),
                item.getItemPrice(),
                item.getItemImgUrl()
        );
    }



}
