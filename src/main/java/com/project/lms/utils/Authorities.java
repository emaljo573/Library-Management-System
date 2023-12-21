package com.project.lms.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Authorities {

    public static Map<String,String> getAuthorities(){
        Map<String,String> map=new HashMap<>();
        List<String> studentAuthorityList= Arrays.asList(Constants.STUDENT_SELF_AUTHORITY,Constants.ACCESS_BOOK_AUTH,Constants.PAYMENT_AUTH);
        List<String> adminAuthList=Arrays.asList(Constants.STUDENT_INFO_AUTHORITY,Constants.CREATE_ADMIN_AUTHORITY,Constants.CREATE_BOOK_AUTH,Constants.ACCESS_BOOK_AUTH,Constants.INITIATE_TXN_AUTH);
        String studentAuth=String.join(Constants.DELIMITER,studentAuthorityList);
        String adminAuth=String.join(Constants.DELIMITER,adminAuthList);
        map.put(Constants.STUDENT_USER,studentAuth);
        map.put(Constants.ADMIN_USER,adminAuth);
        return map;
    }
}
