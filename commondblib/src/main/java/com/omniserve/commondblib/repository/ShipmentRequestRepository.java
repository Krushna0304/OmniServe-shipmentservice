package com.omniserve.commondblib.repository;

import com.omniserve.commondblib.entity.ShipmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRequestRepository extends JpaRepository<ShipmentRequest,String> {


    @Query(
            value = """
                SELECT * 
                FROM shipment_request
                WHERE
                    order_state = :state
                    AND (EXTRACT(EPOCH FROM (:time - timestamp)) / 60) <= :minuteAgo
                """,
            nativeQuery = true
    )
    List<ShipmentRequest> findShipmentsByStateAndTime(
            @Param("state") String state,
            @Param("time") LocalDateTime time,
            @Param("minuteAgo") int minuteAgo
    );



    @Query(
            value = """
                    UPDATE shipment_request 
                    SET order_state = :nextState
                    where
                        shipment_id = :shipmentID AND order_state = :currState
                     RETURNING *
                    """,
            nativeQuery = true
    )
    Optional<ShipmentRequest> getAndSetState(
            @Param("shipmentID")String shipmentID,
            @Param("currState")String currState,
            @Param("nextState")String nextState);



}
