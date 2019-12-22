package com.cjz.filesaveqq.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  author: ChenJingZhuo
 *  date: 2019/11/22
 */

public class FileSaveQQ2 {

    public static boolean saveUserInfo(String account, String password) {

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File SDPath = Environment.getExternalStorageDirectory();
            File file = new File(SDPath, "data.txt");
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write((account + ":" + password).getBytes());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return false;
        }

    }

    public static Map<String, String> getUserInfo() {

        FileInputStream fis = null;
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File SDPath = Environment.getExternalStorageDirectory();
            File file = new File(SDPath, "data.txt");
            try {
                fis=new FileInputStream(file);
                byte[] buffer=new byte[fis.available()];
                fis.read(buffer);
                String content=new String(buffer);
                Map<String,String> userMap=new HashMap<>();
                String[] infos=content.split(":");
                userMap.put("account",infos[0]);
                userMap.put("password",infos[1]);
                return userMap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try{
                    if (fis!=null){
                        fis.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }else {
            return null;
        }
    }

}
