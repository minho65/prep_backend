package com.lgcns.tct_backend.User.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lgcns.tct_backend.Exception.CustomException;
import com.lgcns.tct_backend.Exception.ErrorCode;
import com.lgcns.tct_backend.User.Model.User;
import com.lgcns.tct_backend.User.Model.UserMzListResponse;
import com.lgcns.tct_backend.User.Model.UserResponse;
import com.lgcns.tct_backend.User.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUser(String userId){
        Optional<User> userOpt = Optional.ofNullable(userRepository.selectUser(userId));
        if(userOpt.isEmpty()) throw new CustomException(ErrorCode.INVALID_USER_ID);
        return UserResponse.userModel().user(userOpt.get()).build();
    }

    public UserMzListResponse getUserWithMzList(String userId){
        Optional<UserMzListResponse> userMzListOpt = Optional.ofNullable(userRepository.selectUserWithMzList(userId));
        if(userMzListOpt.isEmpty()) throw new CustomException(ErrorCode.INVALID_USER_ID);
        return userMzListOpt.get();
    }
}
