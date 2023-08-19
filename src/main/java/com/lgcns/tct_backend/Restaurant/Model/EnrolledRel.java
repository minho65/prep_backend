package com.lgcns.tct_backend.Restaurant.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EnrolledRel {
    private String restaurantId;
    private String restaurantListRelId;
    private String enrolledYn;

    public boolean isEnrolled(){
        return "Y".equals(this.enrolledYn);
    }
}
