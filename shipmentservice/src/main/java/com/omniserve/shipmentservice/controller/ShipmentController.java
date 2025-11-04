package com.omniserve.shipmentservice.controller;

import com.omniserve.shipmentservice.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipper")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/getPendingShipments")
    public ResponseEntity<?> getPendingShipments(@RequestParam("minuteAgo") int minuteAgo)
    {
        try{
            return new ResponseEntity<>(shipmentService.getPendingShipments(minuteAgo),HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/acceptShipment")
    public ResponseEntity<?> acceptShipment(@RequestParam("shipmentId") String shipmentId)
    {
        try
        {
           return  shipmentService.acceptShipment(shipmentId) ? new ResponseEntity<>(HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
