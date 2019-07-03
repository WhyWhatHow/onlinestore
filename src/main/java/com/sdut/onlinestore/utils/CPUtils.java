package com.sdut.onlinestore.utils;

import com.sdut.onlinestore.pojo.Category;

import java.util.List;

import java.util.ArrayList;

public class CPUtils {
    //
//    public  static Object getChildens(Integer parentId, ArrayList<T> list){
//        return  null;
//    }
    public List<Category> getChildrens(Integer parentId, List<Category> list) {
        List<Category> arrayList = new ArrayList(100);
        for (Category cat :
                list) {
            if (parentId == cat.getParentid()) {
                arrayList.addAll(getChildrens(cat.getCid(), cat.getChildernList()));

                arrayList.add(cat);
            }

        }
        return arrayList;
    }
}
