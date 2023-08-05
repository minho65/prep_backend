package com.lgcns.tct_backend.Restaurant.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgcns.tct_backend.Restaurant.Model.RestaurantResponse;
import com.lgcns.tct_backend.Restaurant.Service.RestaurantService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/restaurant")
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantResponse>> getValidRestaurant(){
        return ResponseEntity.ok(restaurantService.getValidRestaurant());
    }
}
