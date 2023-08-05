package com.lgcns.tct_backend.MzList.Model;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.lgcns.tct_backend.Util.LocalDateTimeUtil;

import lombok.Getter;

@Getter
public class CreateMzListRequest {
    private String userId;
    private String listName;

    public boolean isValid(){
        return StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(listName);
    }

    public MzList toMzList(){
        return MzList.builder()
                     .listId(UUID.randomUUID().toString())
                     .userId(this.userId)
                     .listName(this.listName)
                     .createdDate(LocalDateTimeUtil.getNowWithLocalDateFormat())
                     .build();
    }
}
