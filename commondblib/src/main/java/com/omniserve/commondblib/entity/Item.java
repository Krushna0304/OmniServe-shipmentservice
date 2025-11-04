package com.omniserve.commondblib.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints = "item_price >= 0")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String itemId;

    @Column(nullable = false)
    private String itemName;

    private String itemDescription;

    @Column(nullable = false)
    private double itemPrice;

    @Column(nullable = false)
    private String itemImgUrl;
}

