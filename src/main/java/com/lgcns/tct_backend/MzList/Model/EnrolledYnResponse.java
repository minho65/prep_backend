package com.lgcns.tct_backend.MzList.Model;

import com.lgcns.tct_backend.Restaurant.Model.EnrolledYnRestuarant;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class EnrolledYnResponse {
    private String listId;
    List<EnrolledYnRestuarant> enrolledYnList;

    @Builder
    public EnrolledYnResponse(String listId, List<EnrolledYnRestuarant> enrolledYnList){
        this.listId = listId;
        this.enrolledYnList = enrolledYnList;
    }
}
