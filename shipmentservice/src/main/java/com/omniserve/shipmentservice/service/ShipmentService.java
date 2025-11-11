package com.omniserve.shipmentservice.service;

import com.omniserve.commondblib.entity.ShipmentInfo;
import com.omniserve.commondblib.entity.ShipmentRequest;
import com.omniserve.commondblib.event.ShipmentRequestEvent;
import com.omniserve.commondblib.repository.ShipmentInfoRepository;
import com.omniserve.commondblib.repository.ShipmentRequestRepository;
import com.omniserve.commondblib.state.OrderState;
import com.omniserve.commondblib.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.omniserve.commondblib.util.Mapper.toDto;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRequestRepository shipmentRequestRepository;

    @Autowired
    private ShipmentInfoRepository shipmentInfoRepository;

    public List<ShipmentRequestEvent> getPendingShipments(int minuteAgo) {
        List<ShipmentRequest> pendingShipments = shipmentRequestRepository.findShipmentsByStateAndTime(OrderState.READY_TO_DELIVER.toString(),LocalDateTime.now(),minuteAgo);

        return pendingShipments.stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Transactional
    public Boolean acceptShipment(String shipmentId)
    {
        Optional<ShipmentRequest>  shipmentRequest = shipmentRequestRepository.getAndSetState(shipmentId, OrderState.READY_TO_DELIVER.toString(),OrderState.IN_SHIPMENT.toString());
        if(shipmentRequest.isPresent()) {
            shipmentInfoRepository.save(Mapper.toEntity(shipmentRequest.get()));
            return true;
        }
        return false;
    }
}
