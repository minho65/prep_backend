package com.lgcns.tct_backend.Util;

import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtility {
    private static final Pattern USER_ID_REGEX = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_-]{3,19}$");

    public static boolean isUserIdValid(String userId) {
		return USER_ID_REGEX.matcher(userId).matches();
	}
}
