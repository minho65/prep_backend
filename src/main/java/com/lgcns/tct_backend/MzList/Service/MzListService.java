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
import com.lgcns.tct_backend.MzList.Repository.MzListRepository;
import com.lgcns.tct_backend.User.Model.User;
import com.lgcns.tct_backend.User.Repository.UserRepository;
import java.util.List;
import com.lgcns.tct_backend.Restaurant.Model.EnrolledYnRestuarant;

import lombok.RequiredArgsConstructor;

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
}
