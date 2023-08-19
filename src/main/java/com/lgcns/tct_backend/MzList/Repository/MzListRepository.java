package com.lgcns.tct_backend.MzList.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lgcns.tct_backend.MzList.Model.MzList;
import com.lgcns.tct_backend.MzList.Model.MzListWithRestaurantsResponse;
import com.lgcns.tct_backend.Restaurant.Model.EnrolledYnRestuarant;

@Mapper
public interface MzListRepository {
    MzList selectMzListByListId(String listId);
    MzListWithRestaurantsResponse selectMzListWithRestaurants(String listId);
    void createMzList(MzList mzList);
    List<EnrolledYnRestuarant> selectEnrolledYnRestaurants(String listId);
}
