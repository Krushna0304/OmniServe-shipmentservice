package com.omniserve.shipmentservice.service;

import com.omniserve.commondblib.entity.Item;
import com.omniserve.commondblib.entity.OrderRequest;
import com.omniserve.commondblib.event.ShipmentRequestEvent;
import com.omniserve.commondblib.repository.ItemRepository;
import com.omniserve.commondblib.repository.OrderRequestRepository;
import com.omniserve.commondblib.util.EventLogPrinter;
import com.omniserve.commondblib.util.Mapper;
import com.omniserve.shipmentservice.exception.ItemNotFound;
import com.omniserve.shipmentservice.exception.OrderNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ShipmentConsumer {

    public final String TOPIC = "shipment_request";
    public final String groupID="shipper-group";

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    @Autowired
    private ItemRepository itemRepository;

    @KafkaListener(topics = TOPIC,groupId = groupID)
    public void consumeShipmentRequest(ShipmentRequestEvent shipmentRequestEvent)
    {
        System.out.println( "+++----------Shipment Received : ---------------+++");
        EventLogPrinter.logShipment(shipmentRequestEvent);

        OrderRequest orderRequest =  orderRequestRepository.findByOrderId(shipmentRequestEvent.getOrderId()).orElseThrow(
                ()->new OrderNotFound(shipmentRequestEvent.getOrderId())
        );
        EventLogPrinter.logOrder(Mapper.toDto(orderRequest));

        Item item = itemRepository.findByItemId(orderRequest.getItemId()).orElseThrow(()-> new ItemNotFound(orderRequest.getItemId()));
        EventLogPrinter.logItem(Mapper.toDto(item));

        System.out.println( "+++-------------------------------------------------------+++");
    }
}
