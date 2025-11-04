package com.omniserve.commondblib.repository;

import com.omniserve.commondblib.entity.OrderRequest;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Repository
public interface OrderRequestRepository extends JpaRepository<OrderRequest,String> {

    Optional<OrderRequest> findByOrderId(String orderId);

    @Query(
            value = """
                UPDATE order_request
                SET order_state = :nextState
                WHERE 
                order_id = :orderId 
                AND order_state = :currState
                RETURNING user_id;
                """,
            nativeQuery = true
    )
    Optional<String> processOrder(
            @Param("orderId") String orderId,
            @Param("currState") String currState,
            @Param("nextState") String nextState
    );

    @Modifying
    @Query(
            value = """
                    SELECT order_request
                    FROM order_request
                    WHERE
                      order_state = :state and ( EXTRACT(EPOCH FROM (:time - timestamp)) / 60) > :minute
                    """,
            nativeQuery = true
    )
    List<OrderRequest> getOrdersByStateAndTime(String state, LocalDateTime time,int minute);
}
