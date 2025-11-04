package com.omniserve.shipmentservice.service;

import com.omniserve.commondblib.entity.ShipmentInfo;
import com.omniserve.commondblib.entity.ShipmentRequest;
import com.omniserve.commondblib.event.ShipmentRequestEvent;
import com.omniserve.commondblib.repository.ShipmentInfoRepository;
import com.omniserve.commondblib.repository.ShipmentRequestRepository;
import com.omniserve.commondblib.state.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRequestRepository shipmentRequestRepository;

    @Autowired
    private ShipmentInfoRepository shipmentInfoRepository;

    public List<ShipmentRequestEvent> getPendingShipments(int minuteAgo)
    {
        List<ShipmentRequest> pendingShipments = shipmentRequestRepository.findShipmentsByStateAndTime(OrderState.READY_TO_DELIVER.toString(),LocalDateTime.now(),minuteAgo);

        return pendingShipments.stream()
                .map(shipment -> new ShipmentRequestEvent(
                        shipment.getShipmentId(),
                        shipment.getOrderId(),
                        shipment.getPickupLongitude(),
                        shipment.getPickupLatitude(),
                        shipment.getDropLongitude(),
                        shipment.getDropLatitude(),
                        shipment.getOrderState(), // Assuming initial shipment state is PENDING
                        shipment.getUserId(),
                        shipment.getTimestamp()
                ))
                .toList();
    }

    @Transactional
    public Boolean acceptShipment(String shipmentId)
    {
        Optional<ShipmentRequest>  shipmentRequest = shipmentRequestRepository.getAndSetState(shipmentId, OrderState.READY_TO_DELIVER.toString(),OrderState.IN_SHIPMENT.toString());
        if(shipmentRequest.isPresent())
        {
            String shipperId = "shipper123";
            ShipmentInfo shipmentInfo = ShipmentInfo.builder()
                    .shipmentId(shipmentRequest.get().getShipmentId())
                    .orderId(shipmentRequest.get().getOrderId())
                    .userId(shipmentRequest.get().getUserId())
                    .shipperId(shipperId)
                    .checkInTime(LocalDateTime.now())
                    .pickupLongitude(shipmentRequest.get().getPickupLongitude())
                    .pickupLatitude(shipmentRequest.get().getPickupLatitude())
                    .dropLatitude(shipmentRequest.get().getDropLatitude())
                    .dropLongitude(shipmentRequest.get().getDropLongitude())
                    .build();

            shipmentInfoRepository.save(shipmentInfo);
            return true;
        }
        return false;
    }
}
