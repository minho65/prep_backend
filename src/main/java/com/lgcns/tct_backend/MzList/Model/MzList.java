package com.lgcns.tct_backend.MzList.Model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MzList {
    private String listId;
    private String userId;
    private String listName;
    private String createdDate;
}
