package com.omniserve.shipmentservice.service;

import com.omniserve.commondblib.entity.ShipmentRequest;
import com.omniserve.commondblib.event.ShipmentRequestEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ShipmentConsumer {

    public final String TOPIC = "shipment_request";
    public final String groupID="shipper-group";

    @KafkaListener(topics = TOPIC,groupId = groupID)
    public void consumeShipmentRequest(ShipmentRequestEvent shipmentRequestEvent)
    {
        System.out.println( "Shipment Received : "+ shipmentRequestEvent.getShipmentId());
    }
}
