package com.lgcns.tct_backend.Restaurant.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestaurantResponse {
    private String restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantCategory;

    @Builder(builderMethodName = "restaurantModel", builderClassName = "RestaurantModel")
    public RestaurantResponse(Restaurant restaurant){
        if(restaurant != null){
            this.restaurantId = restaurant.getRestaurantId();
            this.restaurantName = restaurant.getRestaurantName();
            this.restaurantAddress = restaurant.getRestaurantAddress();
            this.restaurantCategory = restaurant.getRestaurantCategory();
        }
    }
}
