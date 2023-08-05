package com.lgcns.tct_backend.Restaurant.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lgcns.tct_backend.Restaurant.Model.Restaurant;
import com.lgcns.tct_backend.Restaurant.Model.RestaurantResponse;
import com.lgcns.tct_backend.Restaurant.Repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<RestaurantResponse> getValidRestaurant(){
        List<Restaurant> restaurants = restaurantRepository.selectValidRestaurant();

        if(CollectionUtils.isEmpty(restaurants)) return Collections.emptyList();

        return restaurants.stream()
                          .map(restaurant -> RestaurantResponse.restaurantModel()
                                                               .restaurant(restaurant)
                                                               .build())
                          .collect(Collectors.toList());
    }

}
