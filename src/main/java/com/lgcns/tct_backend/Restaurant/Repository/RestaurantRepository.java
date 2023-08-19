package com.lgcns.tct_backend.Restaurant.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lgcns.tct_backend.Restaurant.Model.Restaurant;

@Mapper
public interface RestaurantRepository {
    List<Restaurant> selectValidRestaurant();
}
