package com.lgcns.tct_backend.MzList.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateMzListRestaurantRel {
    private String listId;
    private List<RelRequest> updateList;

    @Getter
    @NoArgsConstructor
    public static class RelRequest{
        private String restaurantId;
        private String enrolledYn;
    }
}
