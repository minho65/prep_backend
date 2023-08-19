package com.lgcns.tct_backend.Restaurant.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EnrolledYnRestuarant {
    private String restaurantId;
    private String restaurantName;
    private String enrolledYn;

    public boolean isEnrolled(){
        return "Y".equals(this.enrolledYn);
    }
}
