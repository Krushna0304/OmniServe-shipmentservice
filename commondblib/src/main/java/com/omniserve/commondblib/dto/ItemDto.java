package com.omniserve.commondblib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String itemId;
    private String itemName;
    private String itemDescription;
    private double itemPrice;
    private String itemImgUrl;

}
