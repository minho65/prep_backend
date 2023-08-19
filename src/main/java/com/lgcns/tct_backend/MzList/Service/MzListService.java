package com.lgcns.tct_backend.MzList.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lgcns.tct_backend.Exception.CustomException;
import com.lgcns.tct_backend.Exception.ErrorCode;
import com.lgcns.tct_backend.MzList.Model.CreateMzListRequest;
import com.lgcns.tct_backend.MzList.Model.EnrolledYnResponse;
import com.lgcns.tct_backend.MzList.Model.MzList;
import com.lgcns.tct_backend.MzList.Model.MzListResponse;
import com.lgcns.tct_backend.MzList.Model.MzListWithRestaurantsResponse;
import com.lgcns.tct_backend.MzList.Model.RestaurantListRel;
import com.lgcns.tct_backend.MzList.Model.UpdateMzListRestaurantRel;
import com.lgcns.tct_backend.MzList.Model.UpdateMzListRestaurantRel.RelRequest;
import com.lgcns.tct_backend.MzList.Model.UpdateRelResponse;
import com.lgcns.tct_backend.MzList.Repository.MzListRepository;
import com.lgcns.tct_backend.User.Model.User;
import com.lgcns.tct_backend.User.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import com.lgcns.tct_backend.Restaurant.Model.EnrolledYnRestuarant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MzListService {

    private final MzListRepository mzListRepository;
    private final UserRepository userRepository;

    public MzListResponse getMzListByListId(String listId){
        Optional<MzList> mzListOpt = Optional.ofNullable(mzListRepository.selectMzListByListId(listId));

        if(mzListOpt.isEmpty()) throw new CustomException(ErrorCode.INVALID_LIST_ID);

        return MzListResponse.mzListModel().mzList(mzListOpt.get()).build();
    }

    public MzListWithRestaurantsResponse getMzListWithRestaurants(String listId){
        Optional<MzListWithRestaurantsResponse> mzListResOpt = Optional.ofNullable(mzListRepository.selectMzListWithRestaurants(listId));

        if(mzListResOpt.isEmpty()) throw new CustomException(ErrorCode.INVALID_LIST_ID);

        return mzListResOpt.get();
    }

    public MzListResponse createMzList(CreateMzListRequest request) {
        Optional<User> userOpt = Optional.ofNullable(userRepository.selectUser(request.getUserId()));
        if(userOpt.isEmpty()) throw new CustomException(ErrorCode.INVALID_USER_ID);

        MzList mzList = request.toMzList();
        mzListRepository.createMzList(mzList);

        Optional<MzList> mzListOpt = Optional.ofNullable(mzListRepository.selectMzListByListId(mzList.getListId()));

        return (mzListOpt.isPresent()) ? MzListResponse.mzListModel().mzList(mzListOpt.get()).build() : null;
    }

    public EnrolledYnResponse getEnrolledYnRestaurantByListId(String listId) {
        Optional<MzList> mzListOpt = Optional.ofNullable(mzListRepository.selectMzListByListId(listId));

        if(mzListOpt.isEmpty()) throw new CustomException(ErrorCode.INVALID_LIST_ID);

        List<EnrolledYnRestuarant> enrolledYnList = mzListRepository.selectEnrolledYnRestaurants(listId);

        return EnrolledYnResponse.builder()
                                 .listId(listId)
                                 .enrolledYnList(enrolledYnList)
                                 .build();
    }

    public UpdateRelResponse updateMzListRestaurantRel(UpdateMzListRestaurantRel reqeust) {
        final String listId = reqeust.getListId();
        Optional<MzList> mzListOpt = Optional.ofNullable(mzListRepository.selectMzListByListId(listId));
        if(mzListOpt.isEmpty()) throw new CustomException(ErrorCode.INVALID_LIST_ID);

        List<RelRequest> relReqeust = reqeust.getUpdateList();
        List<String> requestIds = relReqeust.stream()
                                           .filter(e->"Y".equals(e.getEnrolledYn()))
                                           .map(RelRequest::getRestaurantId)
                                           .toList();

        List<String> enrollIds = new ArrayList<>();
        List<String> deleteIds = new ArrayList<>();

        List<EnrolledYnRestuarant> enrolledYnList = mzListRepository.selectEnrolledYnRestaurants(listId);

        for(EnrolledYnRestuarant enrolledYnRestuarant : enrolledYnList){
            String curRestaurantId = enrolledYnRestuarant.getRestaurantId();
            if(enrolledYnRestuarant.isEnrolled()){
                if(!requestIds.contains(curRestaurantId)){
                    deleteIds.add(curRestaurantId);
                }
            }else{
                if(requestIds.contains(curRestaurantId)){
                    enrollIds.add(curRestaurantId);
                }
            }
        }

        if(!enrollIds.isEmpty()){
            List<RestaurantListRel> createReqList = enrollIds.stream()
                                                             .map(id -> RestaurantListRel.createBuilder()
                                                                                         .listId(listId)
                                                                                         .restaurantId(id)
                                                                                         .build())
                                                             .toList();
            // HashMap<String, Object> requestMap = new HashMap<>();
            // requestMap.put("createList", createReqList);

            log.debug("create : {}", createReqList.toString());

            mzListRepository.createMzListRestaurantRels(createReqList);
        }
        
        return UpdateRelResponse.builder()
                                .listId(listId)
                                .build();
    }
}
