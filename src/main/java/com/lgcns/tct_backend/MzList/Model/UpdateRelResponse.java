package com.lgcns.tct_backend.MzList.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateRelResponse {
    private String listId;

    @Builder
    public UpdateRelResponse(String listId){
        this.listId = listId;
    }
}
