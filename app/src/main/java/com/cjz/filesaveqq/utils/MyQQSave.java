package com.cjz.filesaveqq.utils;

import android.content.Context;

import com.cjz.filesaveqq.bean.MyQQ;
import com.cjz.filesaveqq.sqlite.MySQL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  author: ChenJingZhuo
 *  date: 2019/11/22
 */

public class MyQQSave {
    public static boolean saveUserInfo(Context context, String account, String password){
        long i=new MySQL(context).insert(account,password);
        boolean isSave;
        return (isSave=(i==1)?true:false);
    }

    public static Map<String,String> getUserInfo(Context context) {
        List<MyQQ> myQQList = new ArrayList<>();
        myQQList=new MySQL(context).find(0);
        Map<String, String> userMap = new HashMap<>();
        try{
            if (myQQList!=null){
                userMap.put("account", myQQList.get(0).getAccount());
                userMap.put("password", myQQList.get(0).getPassword());
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return userMap;
    }
}
