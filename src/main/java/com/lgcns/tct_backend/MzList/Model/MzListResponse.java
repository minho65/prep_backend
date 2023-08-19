package com.lgcns.tct_backend.MzList.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MzListResponse {
    private String listId;
    private String listName;
    private String createdDate;

    public MzListResponse(String listId, String listName, String createdDate){
        this.listId = listId;
        this.listName = listName;
        this.createdDate = createdDate;
    }
    
    @Builder(builderMethodName = "mzListModel", builderClassName = "MzListModel")
    public MzListResponse(MzList mzList){
        this.listId = mzList.getListId();
        this.listName = mzList.getListName();
        this.createdDate = mzList.getCreatedDate();
    }
}
