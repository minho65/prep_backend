package com.lgcns.tct_backend.MzList.Model;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class RestaurantListRel {
    private String restaurantListRelId;
    private String restaurantId;
    private String listId;

    @Builder(builderClassName = "CreateBuilder", builderMethodName = "createBuilder")
    private RestaurantListRel(String restaurantId, String listId){
        this.restaurantListRelId = UUID.randomUUID().toString();
        this.restaurantId = restaurantId;
        this.listId = listId;
    }
}
