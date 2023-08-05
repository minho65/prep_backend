package com.lgcns.tct_backend.User.Controller;

import static com.lgcns.tct_backend.Util.UserUtility.isUserIdValid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgcns.tct_backend.Exception.CustomException;
import com.lgcns.tct_backend.Exception.ErrorCode;
import com.lgcns.tct_backend.User.Model.UserMzListResponse;
import com.lgcns.tct_backend.User.Model.UserResponse;
import com.lgcns.tct_backend.User.Service.UserService;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@RequestMapping("/user")
@RestController 
public class UserController {

    private final UserService service;
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable(name = "userId") String userId){
        if(!isUserIdValid(userId)) throw new CustomException(ErrorCode.INVALID_PARAMETER);
        return ResponseEntity.ok(service.getUser(userId));
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<UserMzListResponse> getUserWithMzList(@PathVariable(name = "userId") String userId){
        if(!isUserIdValid(userId)) throw new CustomException(ErrorCode.INVALID_PARAMETER);
        return ResponseEntity.ok(service.getUserWithMzList(userId));
    }
}
